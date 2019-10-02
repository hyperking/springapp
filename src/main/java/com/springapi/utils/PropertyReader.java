// package gov.va.ch33.automation.common.utils;

// import java.io.FileInputStream;
// import java.io.IOException;
// import java.io.InputStream;
// import java.util.Properties;
// import org.apache.logging.log4j.LogManager;
// import org.apache.logging.log4j.Logger;

// public class PropertyReader {
//     private static final Logger logger = LogManager.getLogger(PropertyReader.class);

//     private static Properties automationProperties = null;

//     private static String jRulesBatchDonotSuppressLettersFlag = getSystemProperty(
//             JRULES_BATCH_DONOT_SUPRESS_LETTERS_FLAG);

//     public static String getAutomationProperty(String key) {
//         // Load the properties if necessary
//         if (automationProperties == null) {
//             loadProperties();
//         }
//         return automationProperties.getProperty(key);
//     }

//     private synchronized static void loadProperties() {
//         // If automation properties have been populated by another
//         // thread before we got the lock then return now
//         if (automationProperties != null) {
//             return;
//         }

//         /*
//          * Keshvee Patel Explicitly closing the InputStream when no longer needed as per
//          * best coding practices and to mitigate a high finding identified in the
//          * Fortify Code Scans
//          */
//         automationProperties = new Properties();
//         InputStream stream = null;
//         try {
//             String path = System.getProperty(AUTOMATION_PROPERTIES);
//             if (path == null || "".equals(path)) {
//                 path = AUTOMATION_PROPERTIES;
//                 stream = AutomationProperties.class.getClassLoader().getResourceAsStream(path);
//             } else {
//                 logger.debug("Using automation properties file specified in system property " + path);
//                 stream = new FileInputStream(path);
//             }

//             automationProperties.load(stream);
//         } catch (IOException e) {
//             logger.error("Problem Loading Automation Properties ", e);
//         } finally {
//             try {
//                 if (stream != null)
//                     stream.close();
//             } catch (IOException e) {
//                 logger.error("Problem Loading Automation Properties ", e);
//             }
//         }
//     }

//     private static String getSystemProperty(String strProperty) {
//         return System.getProperty(strProperty);
//     }

//     /**
//      * Set this system property
//      * "-Dautomation.jrulesbatch.donot.supress.letters=true" to not supress letters
//      * for JRules Batch process. If no property is set a default of false is
//      * returned.
//      * 
//      * @return true - if the do not supress letters flat is set to true. false - if
//      *         no property found or do not supress letters is set to false.
//      */
//     public static boolean getJRulesBatchDonotSuppressLettersFlag() {
//         return Boolean.valueOf(jRulesBatchDonotSuppressLettersFlag);
//     }
// }