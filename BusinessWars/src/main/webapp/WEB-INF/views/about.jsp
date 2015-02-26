<!DOCTYPE html>
<%@page import="java.util.TreeSet"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Properties"%>
<%@page import="java.net.InetAddress"%>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>About Details: ${project.version} - ${BUILD_NUMBER}</title>

    <!-- Bootstrap -->
    <!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	
	<!-- Optional theme -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
  	<div style="padding-left: 20px;">
	    <h1>Build Info</h1>
	    <p><strong>Release Number:</strong> ${project.version}</p>
	    <p><strong>Build Number:</strong> ${BUILD_NUMBER}</p>
	    <p><strong>Build Date:</strong> ${project.version}</p>
	    <br/>
	    <h1>Runtime</h1>
	    <p><strong>Host Name:</strong> <%= InetAddress.getLocalHost().getCanonicalHostName() %></p>
	    <p><strong>Environment:</strong> <%= System.getProperty( "env" ) %></p>
	    <p><strong>JRE Version:</strong> <%= System.getProperty( "java.version" ) %></p>
	    <p><strong>OS Version:</strong> <%= System.getProperty( "os.arch" ) + ", " + System.getProperty( "os.name" ) + ", " + System.getProperty( "os.version" ) %></p>
	    <br/>
	    <h1>Properties</h1>
	    <div style="min-width: 400px; max-width: 1000px;">
		    <table class="table table-striped table-condensed table-bordered"><%
		    	Properties props = (Properties) pageContext.findAttribute( "env-props" );
		    	if ( props != null ) {
		    		Set<String> sortedProps = new TreeSet<String>( props.stringPropertyNames() );
		    		for ( String key : sortedProps ) {
		        		out.println( "<tr><td>" + key + "</td><td>" + props.getProperty( key ) + "</td></tr>" );
		    		}
		    	} %>
		    </table>
	    </div>
    </div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	
	<!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
  </body>
</html>