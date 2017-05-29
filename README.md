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
<li><b>Not Authorithed User</b></li>
<li>Hospital Search Result Page</li>
<li>Doctor Search Result Page</li>
</ol>
<b>Patient</b>
<ol>
<li>Hospital Search Result Page</li>
<li>Doctor Search Result Page</li>
</ol>

<p>To run this application you need:</p>
<pre><code>
Java 8,
Maven 3.3.9
PostgreSQL
</code></pre>

<h1>Instalation</h1>
<pre><code>git clone https://github.com/nromanen/Ch-048.git</code></pre>
<p>In src/main/resources/database.propertise you should set name and password of databes of Hospital Seeker application</p>

<h1>Run Application</h1>
<p>To run this test framework you should go to main folder of project(Ch-048), and then execut command:</p>
<pre><code>mvn clean test site</code></pre>
<p>You can specifiy language of execution and browser name after -P. Supported languages: eng, ukr. Supported browsers: firefox, chrome</p>
<pre><code>mvn -P eng,firefox test site</code></pre>
<p>After executing <b>site</b> phase of maven you can get raports about test execution and screenshots of failed test by running target/site/allure-maven-plugin.html file
</p>
