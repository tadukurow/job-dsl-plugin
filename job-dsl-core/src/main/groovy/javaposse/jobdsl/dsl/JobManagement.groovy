package javaposse.jobdsl.dsl;

/**
 * Interface to manage jobs, which the DSL needs to do.
 *
 * @author jryan
 * @author aharmel-law
 */
public interface JobManagement {
    /**
     * Gets (loads) the job configuration for the Jenkins job with the specified name.  If no name is supplied, an empty
     * configuration is returned.
     * @param jobName the name of the job to look up
     * @return the job configuration as XML
     * @throws JobConfigurationNotFoundException
     */
    String getConfig(String jobName) throws JobConfigurationNotFoundException;

    /**
     * Creates or updates the job config for the named Jenkins job with the config provided.
     * @param jobName the name of the new / updated job
     * @param config the new / updated job config
     * @param ignoreExisting do not update existing jobs
     * @throws JobNameNotProvidedException if the jobName is null or blank
     * @throws JobConfigurationMissingException if the config xml is null or blank
     */
    boolean createOrUpdateConfig(String jobName, String config, boolean ignoreExisting) throws JobNameNotProvidedException, JobConfigurationMissingException;

    /**
     * Queue a job to run. Useful for running jobs after they've been created.
     */
    void queueJob(String jobName) throws JobNameNotProvidedException;

    InputStream streamFileInWorkspace(String filePath) throws IOException;
    String readFileInWorkspace(String filePath) throws IOException;

    /**
     * Stream to write to, for stdout.
     * @return PrintWriter
     */
    PrintStream getOutputStream();

    /**
     * Map if variables that should be available to the script.
     */
    Map<String,String> getParameters();

    /**
     * Returns the id of a Credentials object.
     * @param credentialsDescription the description of the credentials to lookup
     * @return id of Credentials or <code>null</code> if no credentials could be found
     */
    String getCredentialsId(String credentialsDescription);
}
