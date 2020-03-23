<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Login Page</title>
    <script src="/SMT/staticContent/js/jquery-1.12.3.min.js"></script>
    <script src="/SMT/staticContent/js/logout.js"></script>
    <!-- Bootstrap Core CSS -->
    <link href="/SMT/staticContent/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="/SMT/staticContent/css/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/SMT/staticContent/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="/SMT/staticContent/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- jQuery -->
    <script src="/SMT/staticContent/js/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="/SMT/staticContent/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="/SMT/staticContent/js/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="/SMT/staticContent/js/sb-admin-2.js"></script>
    
    <div id="report">
		<label id="demo"></label>
		<script>
		   var date = new Date();
		   document.getElementById("demo").innerHTML = date.toDateString();;
		</script>
	</div>

</head>

<body background="/SMT/staticContent/images/1713500.jpg">
	<div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Please Sign In</h3>
                    </div>
                    <div class="panel-body">
                        <form action="Login" method="post">
                            <div>
                                <div class="form-group">
									<label><span class="glyphicon glyphicon-user"></span>Username:</label>
                                    <input class="form-control" placeholder="Username" name="uname" id="uname" type="text" autofocus>
                                </div>
                                <div class="form-group">
									<label><span class="glyphicon glyphicon-eye-open"></span>Password:</label>
                                    <input class="form-control"
                                     placeholder="Password" name="pass" id="pass" type="password" value="">
                                </div>
                               
                                <!-- Change this to a button or input when using this as a form -->
                                <div class="">
	                                <button type="button" onclick="login()" class="btn btn-md btn-lg btn-success btn-md button_hw button_margin_right">  Login </button>
	                                <button type="reset"  class="btn btn-md btn-lg btn-danger btn-md button_hw button_margin_right">  Reset </button>
	                            </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>