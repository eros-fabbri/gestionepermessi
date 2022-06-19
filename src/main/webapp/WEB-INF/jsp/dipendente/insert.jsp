<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html lang="it" class="h-100">
<head>
<jsp:include page="../header.jsp" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath }/assets/css/jqueryUI/jquery-ui.min.css" />
<style>
.ui-autocomplete-loading {
	background: white url("../assets/img/jqueryUI/anim_16x16.gif") right
		center no-repeat;
}

.error_field {
	color: red;
}
</style>
<title>Nuova richiesta</title>

</head>
<body class="d-flex flex-column h-100">
	<jsp:include page="../navbar.jsp" />

	<!-- Begin page content -->
	<main class="flex-shrink-0">
		<div class="container">

			<%-- se l'attributo in request ha errori --%>
			<spring:hasBindErrors name="insert_richiesta_attr">
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
					<h5>Inserisci nuovo elemento</h5>
				</div>
				<div class='card-body'>

					<form:form method="post" enctype="multipart/form-data" modelAttribute="insert_richiesta_attr"
						action="save" novalidate="novalidate" class="row g-3">

						<div class="col-md-12">
							<label for="tipoPermesso" class="form-label">Tipo di
								permesso <span class="text-danger">*</span>
							</label>
							<spring:bind path="tipoPermesso">
								<select id="tipopermesso" class="form-select ${status.error ? 'is-invalid' : ''}"
									name="tipoPermesso" required>
									<option value="" selected>- Selezionare -</option>
									<option value="FERIE"
										${insert_richiesta_attr.tipoPermesso == 'FERIE'?'selected':''}>FERIE</option>
									<option value="MALATTIA"
										${insert_richiesta_attr.tipoPermesso == 'MALATTIA'?'selected':''}>MALATTIA</option>
								</select>
							</spring:bind>
							<form:errors path="tipoPermesso" cssClass="error_field" />
						</div>
						<div class="col-md-6 d-none" id="codicecertificato">
							<label for="codiceCertificato" class="form-label">Codice
								certificato  <span class="text-danger">*</span> </label>
							<spring:bind path="codiceCertificato">
								<input type="text" name="codiceCertificato"
									id="codiceCertificato"
									class="form-control ${status.error ? 'is-invalid' : ''}"
									placeholder="Inserire il codice del certificato"
									value="${insert_richiesta_attr.codiceCertificato }">
							</spring:bind>
							<form:errors path="codiceCertificato" cssClass="error_field" />
						</div>
						<div id="certificatofile" class="col-md-6 d-none">
							<label for="file" class="form-label">Carica
								certificato</label> <input name="file" class="form-control"
								type="file" id="file">
						</div>
						<div class="form-check">
							<input class="form-check-input" name="giornoSingolo" id="giornosingolo" type="checkbox" value="true"
								id="flexCheckDefault"> <label class="form-check-label"
								for="flexCheckDefault"> Giorno Singolo </label>
						</div>
						<div class="col-md-6">
							<fmt:formatDate pattern='yyyy-MM-dd' var="parsedDate" type='date'
								value='${insert_richiesta_attr.dataInizio}' />
						
								<label for="dataInizio" class="form-label">Data inizio <span class="text-danger">*</span></label>
								<spring:bind path="dataInizio">
									<input class="form-control ${status.error ? 'is-invalid' : ''}"
										id="dataInizio" type="date" placeholder="dd/MM/yy"
										title="formato : gg/mm/aaaa" name="dataInizio"
										value="${parsedDate}">
								</spring:bind>
								<form:errors path="dataInizio" cssClass="error_field" />
						
						</div>
						<div class="col-md-6">
							<fmt:formatDate pattern='yyyy-MM-dd' var="parsedDate" type='date'
								value='${insert_richiesta_attr.dataFine}' />

							<label for="dataFine" class="form-label">Data fine <span id="finerequired" class="text-danger">*</span></label>
							<spring:bind path="dataFine">
								<input
									class="form-control disabled ${status.error ? 'is-invalid' : ''}"
									id="dataFine" type="date" placeholder="dd/MM/yy"
									title="formato : gg/mm/aaaa" name="dataNascita"
									value="${parsedDate}">
							</spring:bind>
							<form:errors path="dataFine" cssClass="error_field" />

						</div>
						<div class="form-horizontal">
							<div class="form-group">
							<label for="nota" class="form-label">Nota</label>
								<div class="col-md-12">
								
									<textarea class="form-control" id="nota" name="nota" rows="3"></textarea>
								</div>
							</div>
						</div>
						<div class="col-12">
							<button type="submit" name="submit" value="submit" id="submit"
								class="btn btn-primary">Conferma</button>
						</div>
						
						<input type="hidden" name="userId" value="${userInfo.id}">

					</form:form>
					<script>
						$(document).ready(function() {
							$('#tipopermesso').on('change', function() {
								if (this.value == 'MALATTIA') {
									$("#codicecertificato").removeClass('d-none');
									$("#certificatofile").removeClass('d-none');
									
								} else {
									$("#codicecertificato").addClass('d-none');
									$("#certificatofile").addClass('d-none');
									
								}
							});
							$(document).on('change', '#giornosingolo', function() {
							    if(this.checked) {
							    	$("#dataFine").prop('disabled',true);
							    	$("#finerequired").addClass('d-none');
							    }else{
							    	$("#dataFine").prop('disabled',false); 
							    	$("#finerequired").removeClass('d-none');
							    }
							});
						});
					</script>

					<!-- end card-body -->
				</div>
				<!-- end card -->
			</div>
			<!-- end container -->
		</div>

		<!-- end main -->
	</main>
	<jsp:include page="../footer.jsp" />

</body>
</html>