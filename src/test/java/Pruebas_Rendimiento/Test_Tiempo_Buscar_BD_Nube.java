package Pruebas_Rendimiento;

import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.protocol.http.sampler.HTTPSampler;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.samplers.SampleSaveConfiguration;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.threads.JMeterContextService;
import org.apache.jmeter.threads.JMeterVariables;
import org.apache.jmeter.threads.ThreadGroup;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import org.apache.jorphan.collections.ListedHashTree;
import org.apache.jmeter.protocol.http.control.Header;
import org.apache.jmeter.protocol.http.control.HeaderManager;

/**
 * @author Sebastian Manquillo
 */
public class Test_Tiempo_Buscar_BD_Nube {

    // Configuración general de Firestore
    private static final String PROJECT_ID = "tienda-electronica-174f6";

    public static void main(String[] args) {
        String uid = "7150"; // UID de prueba (document ID en Firestore)

        // Obtener token de Firebase
        String accessToken = Generador_Token_Firebase.getAccessTokenFromServiceAccount();

        if (accessToken != null) {
            System.out.println("Token obtenido exitosamente.");
            ejecutarPruebaFirestore(uid, PROJECT_ID, accessToken);
        } else {
            System.err.println("No se pudo obtener el token de acceso. Revisa la ruta del JSON.");
        }
    }

    // Prueba de rendimiento
    public static void ejecutarPruebaFirestore(String uid, String projectId, String accessToken) {
        try {
            System.out.println("Iniciando prueba Firestore con UID: " + uid);

            // 1. Inicialización de JMeter
            String jmeterHome = "C:\\Users\\Lenovo\\OneDrive\\Documentos\\apache-jmeter-5.6.3";
            JMeterUtils.setJMeterHome(jmeterHome);
            JMeterUtils.loadJMeterProperties(jmeterHome + "/bin/jmeter.properties");
            JMeterUtils.initLocale();
            JMeterContextService.getContext().setVariables(new JMeterVariables());
            SaveService.loadProperties();

            StandardJMeterEngine jmeter = new StandardJMeterEngine();

            // 2. Configurar HTTPSampler para la API REST de Firestore (GET documento)
            HTTPSampler httpSampler = new HTTPSampler();
            httpSampler.setName("Busqueda_Firestore_Documento_UID_" + uid);
            httpSampler.setDomain("firestore.googleapis.com");
            httpSampler.setPort(443);
            httpSampler.setProtocol("https");
            httpSampler.setMethod("GET");

            // Acceso directo al documento: Persona/{uid}
            httpSampler.setPath("/v1/projects/" + projectId + "/databases/(default)/documents/Persona/" + uid);
            httpSampler.setFollowRedirects(true);
            httpSampler.setUseKeepAlive(true);
            httpSampler.setDoMultipartPost(false);

            // 3. Encabezados HTTP
            HeaderManager headerManager = new HeaderManager();
            headerManager.setName("Firestore Headers");
            headerManager.add(new Header("Authorization", "Bearer " + accessToken));
            headerManager.add(new Header("Accept", "application/json"));
            headerManager.add(new Header("User-Agent", "Apache-JMeter-Firestore-Test"));

            // 4. Controladores
            LoopController loopController = new LoopController();
            loopController.setLoops(5); // Iteraciones por hilo
            loopController.initialize();

            ThreadGroup threadGroup = new ThreadGroup();
            threadGroup.setName("Grupo_Firestore_UID_" + uid);
            threadGroup.setNumThreads(10); // Hilos (usuarios simultáneos)
            threadGroup.setRampUp(5); // Tiempo de subida
            threadGroup.setSamplerController(loopController);

            TestPlan testPlan = new TestPlan("Prueba_Firestore_UID_" + uid);

            // 5. Configuración de resultados
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
            saveConfig.setBytes(true);
            saveConfig.setSentBytes(true);
            saveConfig.setUrl(true);
            saveConfig.setIdleTime(true);
            saveConfig.setConnectTime(true);

            ResultCollector logger = new ResultCollector();
            logger.setSaveConfig(saveConfig);
            logger.setFilename(
                "C:\\Users\\Lenovo\\OneDrive\\Documentos\\NetBeansProjects\\Tienda_Electronica\\src\\test\\java\\Pruebas_Rendimiento\\resultados_firestore_uid_" 
                + uid + ".jtl"
            );

            // 6. Construcción del árbol de ejecución
            ListedHashTree testPlanTree = new ListedHashTree();
            HashTree planTree = testPlanTree.add(testPlan);
            HashTree threadGroupTree = planTree.add(threadGroup);
            threadGroupTree.add(httpSampler);
            threadGroupTree.add(headerManager);
            threadGroupTree.add(logger);

            // 7. Ejecución
            jmeter.configure(testPlanTree);
            jmeter.run();

            System.out.println("Prueba Firestore completada. Resultados en:");
            System.out.println("C:\\Users\\Lenovo\\OneDrive\\Documentos\\NetBeansProjects\\Tienda_Electronica\\src\\test\\java\\Pruebas_Rendimiento\\resultados_firestore_uid_" 
                + uid + ".jtl");

        } catch (Exception e) {
            System.err.println("Error durante la ejecución de prueba Firestore:");
            e.printStackTrace();
        }
    }
}
