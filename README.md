<h1>Ch-048</h1>
<br/>
<h4>About project</h4>
<p>This project is a test framwork for testing Hospital Seeker(https://bitbucket.org/nromanen/ch-043/branches/) web application. It provides end-to-end functional UI testing and performance testing. All pages of this project were described with help of Page Factory Pattern. </p>
<p>Fully tested pages: </p>
<b>Admin</b>
<ol>
<li>Add User Page</li>
<li>All User Page</li>
<li>Add New Hospital Page</li>
<li>Hospital List Page</li>
</ol>
<b>Manager</b>
<ol>
<li>Scheduler Page</li>
<li>Manager Dashbord</li>
<li>Manager Feedback Page</li>
</ol>
<b>Doctor</b>
<ol>
<li>Create New Record Page</li>
<li>List Patients Page</li>
<li>Work Scheduler Page</li>
</ol>
<b>Authorized Users</b>
<ol>
<li>Feedback Create Page</li>
</ol>
<b>Not Authorithed User</b>
<ol>
<li>Hospital Search Result Page</li>
<li>Doctor Search Result Page</li>
</ol>
<b>Patient</b>
<ol>
<li>Hospital Search Result Page</li>
<li>Doctor Search Result Page</li>
</ol>

<p>To run this application you need:</p>
<pre><code>Mozilla firefox v.52.0.2,
Java 8,
Maven 3.3.9,
PostgreSQL 9.6
</code></pre>

<h1>Run Hospital Seeker as a docker containers</h1>
<p>In order to set up Hospital Seeker project in a docker container you need Docker with version 17.03.1-ce or higher and Docker-Compose version 1.14.0-rc1 or higher(All instructions of how todownload docker are <a href = "https://docs.docker.com/engine/installation/">here</a>). Download <a href="https://github.com/nromanen/Ch-048/blob/master/HospitalSeeker/docker-compose.yml">this file</a> and put in any folder on your local disk. In order to run this container make sure you've got free port http://localhost:5432, if it's busy try to turn off your Postgresql service. To run containers, using Command Line mover to folder with docker-compose.yml. Run command:</p>
<pre><code>docker-compose up</code></pre>
<p>After executing of this command you should wait for 3-4 minutes for postgres and tomcat to be up. After this Hospital Seeker will be available on the url https://localhost:8443/HospitalSeeker/ and it's database will be available on the url http://localhost:5432/ . In oder to stop this app you shuld press combination ctrl+C in the terminal window of docker compose.</p>


<h1>Instalation</h1>
<pre><code>git clone https://github.com/nromanen/Ch-048.git</code></pre>
<p>In src/main/resources/database.propertise you should set name and password of databes of Hospital Seeker application</p>

<h1>Run Application</h1>
<p>To run this test framework you should go to main folder of project(Ch-048), and then execut command:</p>
<pre><code>mvn clean test site</code></pre>
<p>You can specifiy language of execution and browser name after -P. Supported languages: eng, ukr. Supported browsers: firefox, chrome</p>
<pre><code>mvn -P eng,firefox test site</code></pre>
<p>After executing <b>"site"</b> phase of maven you can get raports about test execution and screenshots of failed test by running target/site/allure-maven-plugin.html file
</p>
