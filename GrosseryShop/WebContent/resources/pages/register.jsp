<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Send Email</title>
<%@include file="include.jsp"%>

<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>

<script src="${pageContext.request.contextPath}/resources/js/app.js"></script>
<script
	src="//ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular-animate.js"></script>

<script
	src="//ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular-sanitize.js"></script>

<script type="text/javascript">
	
</script>
</head>
<body data-open="click" data-menu="vertical-menu" data-col="1-column"
	class="vertical-layout vertical-menu 1-column  blank-page blank-page"
	ng-app="EmailManagement" ng-controller="EmailController">



	<div class="app-content content container-fluid">
		<div class="content-wrapper">
			<div class="content-header row"></div>
			<div class="content-body">
				<section class="flexbox-container">
				<div
					class="col-md-4 offset-md-4 col-xs-10 offset-xs-1 box-shadow-2 p-0">
					<div class="card border-grey border-lighten-3 px-2 py-2 m-0">
						<div class="card-header no-border">
							<div class="card-title text-xs-center">
								<img src="../../app-assets/images/logo/robust-logo-dark.png"
									alt="branding logo">
							</div>
							<h6
								class="card-subtitle line-on-side text-muted text-xs-center font-small-3 pt-2">
								<span>Create Account</span>
							</h6>
						</div>
						<div class="card-body collapse in">
							<div class="card-block">
								<form name="sms" method="post" ng-submit="registerUser()">
									<fieldset
										class="form-group position-relative has-icon-left mb-1">
										<input type="tel"
											class="form-control form-control-lg input-lg" id="user-email"
											placeholder="Your Phone" pattern="[0-9]{10}" ng-model="registerForm.phone" required>
										<div class="form-control-position">
											<i class="icon-android-phone-portrait"></i>
										</div>
									</fieldset>
									<fieldset
										class="form-group position-relative has-icon-left mb-1">
										<input type="phone"
											class="form-control form-control-lg input-lg" ng-model="registerForm.fname" id="user-email"
											placeholder="First Name" required>
										<div class="form-control-position">
											<i class="icon-android-phone-portrait"></i>
										</div>
									</fieldset>
									<fieldset
										class="form-group position-relative has-icon-left mb-1">
										<input type="phone"
											class="form-control form-control-lg input-lg" id="user-email"
											placeholder="Last Name" ng-model="registerForm.lname" required>
										<div class="form-control-position">
											<i class="icon-android-phone-portrait"></i>
										</div>
									</fieldset>
									<fieldset
										class="form-group position-relative has-icon-left mb-1">
										<input type="phone"
											class="form-control form-control-lg input-lg" id="user-email"
											placeholder="Your Address" ng-model="registerForm.address" required>
										<div class="form-control-position">
											<i class="icon-android-phone-portrait"></i>
										</div>
									</fieldset>
									
									<fieldset
										class="form-group position-relative has-icon-left mb-1">
										<input type="email"
											class="form-control form-control-lg input-lg" id="user-email"
											placeholder="Your Email Address" ng-model="registerForm.email" >
										<div class="form-control-position">
											<i class="icon-mail6"></i>
										</div>
									</fieldset>
									
									<fieldset class="form-group position-relative has-icon-left">
										<input type="password"
											class="form-control form-control-lg input-lg"
											id="user-password" placeholder="Enter Password" required>
										<div class="form-control-position">
											<i class="icon-key3"></i>
										</div>
									</fieldset>
									<fieldset class="form-group position-relative has-icon-left">
										<input type="password"
											class="form-control form-control-lg input-lg"
											id="user-password" placeholder="Re-Enter Password" ng-model="registerForm.password" required>
										<div class="form-control-position">
											<i class="icon-key3"></i>
										</div>
									</fieldset>
									<!--  <input type = "file" fileread="emailForm.pathOfXL"  /> -->

									<button type="submit" class="btn btn-primary btn-lg btn-block">
										<i class="icon-unlock2"></i> Register
									</button>

								</form>
							</div>
							<p class="text-xs-center">
								Already have an account ? <a href="login" class="card-link">Login</a>
							</p>
						</div>
					</div>
				</div>
				</section>
			</div>
		</div>
	</div>
	<div class="modal fade" id="modals" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">

					<h4 class="modal-title" id="myModalLabel">Enter your OTP Below that has been sent to your mail id: {{registerForm.email}}</h4>
				</div>
				<div class="modal-body">
					<div class="alert alert-danger" ng-hide="otpstatus">
								<p>Invalid OTP. Please retry again...</p>
							</div>
												<label>OTP</label>
												<div class="form-group">
													<input type="text" ng-model="otpvalue" id="otp"
														class="form-control">
												</div>
										
				</div>
				<div class="modal-footer">
						<button type="button" class="btn grey btn-outline-secondary" ng-click="reloadPage()" data-dismiss="modal">Cancel</button>
											<button type="button" class="btn btn-outline-primary" ng-click="validateOtp()">Validate</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="modalSuccess" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">

					<h4 class="modal-title" id="myModalLabel">Registration Status</h4>
				</div>
				<div class="modal-body">
					<div>
								<p ng-bind-html="finalMSG"></p>
							</div>
												
										
				</div>
				<div class="modal-footer">
						<button type="button" class="btn grey btn-outline-secondary" ng-click="doAction('login')" data-dismiss="modal">Ok</button>
											
				</div>
			</div>
		</div>
	</div>

	<%@include file="footer.jsp"%>





</body>
</html>