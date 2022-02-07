Trace Compass Test Traces
=========================

This tree contains a set of CTF test traces, primarily for use in Trace Compass.

To build the package and install it in your local Maven repo, simply isssue

    mvn clean install

You can also use the `deploy` target to populate both a standard Maven repo and
a p2 update site. The `-Dmaven-deploy-destination` and `-Dp2-deploy-destination`
properties can be used to specify their respective deploy locations.
For example:

    mvn clean deploy -Dmaven-deploy-destination=file:///var/www/traces/maven -Dp2-deploy-destination=/var/www/traces/repository

(Note that the first property needs a `file:///` scheme, but the second does not.)

You can then point depending projects to these locations.

By default a snapshot version is built and deployed. To deploy a release
version for the Maven repo, the pom.xml files need to be updated to remove the
`SNAPSHOT` tag from pom.xml files. This is not needed when deploying p2
update sites.

Adding a new test trace (CTF or Ftrace)
---------------------------------------

Note the description below describes the procedure for CTF. For Ftrace, the
procedure is the same. Just replace `ctf` with `ftrace` and `Ctf` with `Ftrace`
respectively.

The modules follow the [Maven standard directory layout][].

To add a new CTF test trace, add it to the `ctf/src/main/resources` directory.
Make sure it is not archived or anything, as this will be exposed as-is to the
users.

Then update the `ctf/src/main/java/.../CtfTestTrace.java` file accordingly to
include the new trace.

Make sure the parameters (event count, etc.) are correct! This project does not
check those at the moment, but if they are incorrect they **will** fail some
Trace Compass unit tests. This is a known issue.

Finally, bump the project's minor version (1.7.0 -> 1.8.0) in the main pom.xml
and related `<parent>` blocks.

Deploying the repo and update site
----------------------------------

As opposed to the previous Eclipse CI infrastructure setup, the new setup
doesn't have direct write access to the deployment server, and hence the Maven
repo and p2 update site cannot be copied directly.

The [Release Jenkins Job][] is now configured to deploy the artificats to the
deployment server. A committer in the Eclipse Trace Compass project can
trigger this build manually to make a release. After the release is done
tag the version with the new version tag, for example `1.8.0`.

* p2 update site (for use in Eclipse .target files). Replace `1.8.0` with your required version.
  * <http://archive.eclipse.org/tracecompass/tracecompass-test-traces/repository/latest/>
  * <http://archive.eclipse.org/tracecompass/tracecompass-test-traces/repository/1.8.0/>
* Standard Maven repo (for use in Maven `<dependency>` blocks)
  * <http://archive.eclipse.org/tracecompass/tracecompass-test-traces/maven/>

The [Nighly Jenkins Job][] is configured to deploy a nighly p2 updates site
which is automatically triggered when the source code repository is updated.

* p2 update site (for use in Eclipse .target files)
  * <http://archive.eclipse.org/tracecompass/tracecompass-test-traces/repository/nightly/>

Note: Starting with version `1.8.0` only p2 update sites are maintained and the
Maven repo is not deployed anymore.

[Maven standard directory layout]: https://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout.html
[Release Jenkins Job]: https://ci.eclipse.org/tracecompass/view/Misc/job/tracecompass-test-traces-release
[Nighly Jenkins Job]: https://ci.eclipse.org/tracecompass/view/Misc/job/tracecompass-test-traces-nightly