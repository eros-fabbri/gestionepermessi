<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html lang="it" class="h-100">
<head>

<!-- Common imports in pages -->
<jsp:include page="./header.jsp" />
<style>
.error_field {
	color: red;
}
</style>

<title>Modifica Password</title>
</head>
<body class="d-flex flex-column h-100">

	<!-- Fixed navbar -->
	<jsp:include page="./navbar.jsp"></jsp:include>


	<!-- Begin page content -->
	<main class="flex-shrink-0">
		<div class="container">

			<%-- se l'attributo in request ha errori --%>
			<spring:hasBindErrors name="insert_utente_attr">
				<%-- alert errori --%>
				<div class="alert alert-danger " role="alert">Attenzione!!
					Sono presenti errori di validazione</div>
			</spring:hasBindErrors>

			<div
				class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }"
				role="alert">
				${errorMessage}
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>

			<div class='card'>
				<div class='card-header'>
					<h5>Modifica Password</h5>
				</div>
				<div class='card-body'>

					<h6 class="card-title">
						I campi con <span class="text-danger">*</span> sono obbligatori
					</h6>


					<form class="form-signin" name='reset' action="confirmreset" method='POST'
						novalidate="novalidate">

						<div class="col-md-3">
							<label for="password" class="form-label">Vecchia Password
								<span class="text-danger">*</span>
							</label> <input type="password"
								class="form-control ${status.error ? 'is-invalid' : ''}"
								name="oldPassword" id="oldPassword"
								placeholder="Inserire Password" required>

							<form:errors path="password" cssClass="error_field" />
						</div>

						<div class="col-md-3">
							<label for="password" class="form-label">Nuova Password <span
								class="text-danger">*</span></label> <input type="password"
								class="form-control ${status.error ? 'is-invalid' : ''}"
								name="newPassword" id="newPassword"
								placeholder="Inserire Password" required>
							<form:errors path="password" cssClass="error_field" />
						</div>

						<div class="col-md-3">
							<label for="confermaPassword" class="form-label">Conferma
								Nuova Password <span class="text-danger">*</span>
							</label> <input type="password"
								class="form-control ${status.error ? 'is-invalid' : ''}"
								name="confermaPassword" id="confermaPassword"
								placeholder="Confermare Password" required>

							<form:errors path="confermaPassword" cssClass="error_field" />
						</div>
						<br>


						<div class="col-12">
							<input type="hidden" name="idUser" id="idUser"
								value="${change_pass_attr.id }" />
							<button type="submit" name="submit" value="submit" id="submit"
								class="btn btn-primary">Cambia Password</button>
							<input class="btn btn-outline-warning" type="reset"
								value="Ripulisci">
						</div>

					</form>



					<!-- end card-body -->
				</div>
				<!-- end card -->
			</div>


			<!-- end container -->
		</div>

	</main>

	<!-- Footer -->
	<jsp:include page="./footer.jsp" />
</body>
</html>