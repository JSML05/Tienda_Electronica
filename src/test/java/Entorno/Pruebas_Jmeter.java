/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Entorno;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.config.ConfigTestElement;
import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.protocol.jdbc.config.DataSourceElement;
import org.apache.jmeter.protocol.jdbc.sampler.JDBCSampler;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.samplers.SampleSaveConfiguration;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.threads.JMeterContextService;
import org.apache.jmeter.threads.JMeterVariables;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import org.apache.jmeter.threads.ThreadGroup;
import org.apache.jorphan.collections.ListedHashTree;

/**
 *
 * @author Santiago Lopez
 */
public class Pruebas_Jmeter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            // 1. Inicialización de JMeter
            String jmeterHome = "C:\\Users\\Lenovo\\OneDrive\\Documentos\\apache-jmeter-5.6.3";
            JMeterUtils.setJMeterHome(jmeterHome);
            JMeterUtils.loadJMeterProperties(jmeterHome + "/bin/jmeter.properties");
            JMeterUtils.initLocale();
            
            // Inicialización del contexto
            JMeterContextService.getContext().setVariables(new JMeterVariables());
            SaveService.loadProperties();

            // 2. Motor de ejecución
            StandardJMeterEngine jmeter = new StandardJMeterEngine();

            // 3. Configuración JDBC (con validación de parámetros numéricos)
            DataSourceElement jdbcConfig = new DataSourceElement();
            jdbcConfig.setName("MySQLConfig");
            jdbcConfig.setProperty("dataSource", "prueba");
            jdbcConfig.setProperty("driver", "com.mysql.cj.jdbc.Driver");
            jdbcConfig.setProperty("dbUrl", "jdbc:mysql://localhost:3323/prueba?useSSL=false&serverTimezone=UTC");
            jdbcConfig.setProperty("username", "root");
            jdbcConfig.setProperty("password", "");
            
            // Configuración numérica con valores por defecto
            setNumericProperty(jdbcConfig, "poolMax", "10");
            setNumericProperty(jdbcConfig, "timeout", "10000");
            setNumericProperty(jdbcConfig, "trimInterval", "60000");

            // 4. JDBC Sampler
            JDBCSampler jdbcSampler = new JDBCSampler();
            jdbcSampler.setName("Consulta personas");
            jdbcSampler.setProperty("dataSource", "prueba");
            jdbcSampler.setProperty("queryType", "Select Statement");
            jdbcSampler.setProperty("query", "SELECT nombre FROM persona;");
            setNumericProperty(jdbcSampler, "queryTimeout", "30");
            jdbcSampler.setResultSetHandler("Store as String");
            jdbcSampler.setProperty("variableNames", "NOMBRE");

            // 5. Loop Controller
            LoopController loopController = new LoopController();
            loopController.setLoops(1);
            loopController.initialize();

            // 6. Thread Group
            ThreadGroup threadGroup = new ThreadGroup();
            threadGroup.setName("Grupo de Hilos");
            threadGroup.setNumThreads(1);
            threadGroup.setRampUp(1);
            threadGroup.setSamplerController(loopController);

            // 7. Test Plan
            TestPlan testPlan = new TestPlan("Plan JDBC");

            // 8. Configuración de resultados
            SampleSaveConfiguration saveConfig = new SampleSaveConfiguration();
            saveConfig.setAsXml(true);
            saveConfig.setSamplerData(true);
            saveConfig.setResponseData(true);
            saveConfig.setResponseHeaders(true);
            saveConfig.setFieldNames(true);

            // 9. Recolector de resultados
            ResultCollector logger = new ResultCollector();
            logger.setSaveConfig(saveConfig);
            logger.setFilename("C:\\Users\\Lenovo\\OneDrive\\Documentos\\NetBeansProjects\\Tienda_Electronica\\src\\test\\java\\Entorno\\resultados.jtl");

            // 10. Construcción del árbol
            ListedHashTree testPlanTree = new ListedHashTree();
            HashTree planTree = testPlanTree.add(testPlan);
            planTree.add(jdbcConfig);
            HashTree threadGroupTree = planTree.add(threadGroup);
            threadGroupTree.add(jdbcSampler);
            threadGroupTree.add(logger);

            // 11. Ejecución
            jmeter.configure(testPlanTree);
            jmeter.run();

            System.out.println("Prueba completada. Resultados en:");
            System.out.println("C:/Users/LENOVO/Documents/resultados.jtl");

        } catch (Exception e) {
            System.err.println("Error durante la ejecución:");
            e.printStackTrace();
        }
    }

    // Método helper para asignar propiedades numéricas de forma segura
    private static void setNumericProperty(Object element, String name, String defaultValue) {
        try {
            if (element instanceof DataSourceElement) {
                ((DataSourceElement)element).setProperty(name, defaultValue);
            } else if (element instanceof JDBCSampler) {
                ((JDBCSampler)element).setProperty(name, defaultValue);
            }
        } catch (NumberFormatException e) {
            System.err.println("Valor inválido para " + name + ". Usando valor por defecto: " + defaultValue);
            if (element instanceof DataSourceElement) {
                ((DataSourceElement)element).setProperty(name, defaultValue);
            } else if (element instanceof JDBCSampler) {
                ((JDBCSampler)element).setProperty(name, defaultValue);
            }
        }
    }
}
