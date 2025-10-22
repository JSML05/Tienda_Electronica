/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Pruebas_Rendimiento;

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
 * @author Sebastian Manquillo
 */
public class Test_Tiempo_Buscar_BD_Local {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Parámetro de entrada para la base de datos local
        String uid = "123";
        ejecutarPruebaBDLocal(uid);
    }

    // Prueba de rendimiento
    public static void ejecutarPruebaBDLocal(String uid) {
        try {
            System.out.println("Iniciando prueba BD Local con UID: " + uid);

            // 1. Inicialización de JMeter
            String jmeterHome = "C:\\Users\\Lenovo\\OneDrive\\Documentos\\apache-jmeter-5.6.3";
            JMeterUtils.setJMeterHome(jmeterHome);
            JMeterUtils.loadJMeterProperties(jmeterHome + "/bin/jmeter.properties");
            JMeterUtils.initLocale();

            JMeterContextService.getContext().setVariables(new JMeterVariables());
            SaveService.loadProperties();

            // 2. Motor de ejecución
            StandardJMeterEngine jmeter = new StandardJMeterEngine();

            // 3. Configuración JDBC para MySQL
            DataSourceElement jdbcConfig = new DataSourceElement();
            jdbcConfig.setName("MySQLConfig");
            jdbcConfig.setProperty("dataSource", "mysql_local");
            jdbcConfig.setProperty("driver", "com.mysql.cj.jdbc.Driver");
            jdbcConfig.setProperty("dbUrl", "jdbc:mysql://localhost:3323/prueba?useSSL=false&serverTimezone=UTC");
            jdbcConfig.setProperty("username", "root");
            jdbcConfig.setProperty("password", "");

            setNumericProperty(jdbcConfig, "poolMax", "10");
            setNumericProperty(jdbcConfig, "timeout", "10000");
            setNumericProperty(jdbcConfig, "trimInterval", "60000");

            // 4. JDBC Sampler para consulta con UID - CORREGIDO
            JDBCSampler jdbcSampler = new JDBCSampler();
            jdbcSampler.setName("Busqueda_Personas_MySQL_UID_" + uid);
            jdbcSampler.setProperty("dataSource", "mysql_local");
            jdbcSampler.setProperty("queryType", "Prepared Select Statement");

            // Consulta CORREGIDA para usar la tabla 'persona' y buscar por Uid
            String searchQuery = "SELECT * FROM persona WHERE Uid = ? OR Cedula = ?";
            jdbcSampler.setProperty("query", searchQuery);
            jdbcSampler.setProperty("queryArguments", uid + "," + uid);
            jdbcSampler.setProperty("queryArgumentsTypes", "VARCHAR,VARCHAR");

            setNumericProperty(jdbcSampler, "queryTimeout", "30");
            jdbcSampler.setResultSetHandler("Store as String");

            // 5. Loop Controller
            LoopController loopController = new LoopController();
            loopController.setLoops(5); // 5 iteraciones
            loopController.initialize();

            // 6. Thread Group
            ThreadGroup threadGroup = new ThreadGroup();
            threadGroup.setName("Grupo_BD_Local_UID_" + uid);
            threadGroup.setNumThreads(10); // 10 usuarios
            threadGroup.setRampUp(5); // 5 segundos para alcanzar 10 usuarios
            threadGroup.setSamplerController(loopController);

            // 7. Test Plan
            TestPlan testPlan = new TestPlan("Prueba_BD_Local_UID_" + uid);

            // 8. Configuración de resultados
            SampleSaveConfiguration saveConfig = new SampleSaveConfiguration();
            saveConfig.setAsXml(true);
            saveConfig.setTime(true);
            saveConfig.setLatency(true);
            saveConfig.setTimestamp(true);
            saveConfig.setSuccess(true);
            saveConfig.setLabel(true);
            saveConfig.setCode(true);
            saveConfig.setMessage(true);
            saveConfig.setThreadName(true);

            // 9. Recolector de resultados
            ResultCollector logger = new ResultCollector();
            logger.setSaveConfig(saveConfig);
            logger.setFilename("C:\\Users\\Lenovo\\OneDrive\\Documentos\\NetBeansProjects\\Tienda_Electronica\\src\\test\\java\\Pruebas_Rendimiento\\resultados_bd_local_uid_" + uid + ".jtl");

            // 10. Construcción del árbol
            ListedHashTree testPlanTree = new ListedHashTree();
            HashTree planTree = testPlanTree.add(testPlan);
            planTree.add(jdbcConfig);
            HashTree threadGroupTree = planTree.add(threadGroup);
            threadGroupTree.add(jdbcSampler);
            threadGroupTree.add(logger);

            // 11. Ejecución
            System.out.println("Iniciando prueba de Base de Datos Local con UID: " + uid);
            jmeter.configure(testPlanTree);
            jmeter.run();

            System.out.println("Prueba BD Local completada. Resultados en:");
            System.out.println("C:\\Users\\Lenovo\\OneDrive\\Documentos\\NetBeansProjects\\Tienda_Electronica\\src\\test\\java\\Pruebas_Rendimiento\\resultados_bd_local_uid_" + uid + ".jtl");

        } catch (Exception e) {
            System.err.println("Error durante la ejecución de prueba BD Local:");
            e.printStackTrace();
        }
    }

    private static void setNumericProperty(Object element, String name, String defaultValue) {
        try {
            if (element instanceof DataSourceElement) {
                ((DataSourceElement) element).setProperty(name, defaultValue);
            } else if (element instanceof JDBCSampler) {
                ((JDBCSampler) element).setProperty(name, defaultValue);
            }
        } catch (NumberFormatException e) {
            System.err.println("Valor inválido para " + name + ". Usando valor por defecto: " + defaultValue);
            if (element instanceof DataSourceElement) {
                ((DataSourceElement) element).setProperty(name, defaultValue);
            } else if (element instanceof JDBCSampler) {
                ((JDBCSampler) element).setProperty(name, defaultValue);
            }
        }
    }
}

