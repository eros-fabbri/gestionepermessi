<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it" class="h-100">
<head>
<jsp:include page="../../header.jsp" />
<title>Ricerca</title>


</head>
<body class="d-flex flex-column h-100">
	<!-- Fixed navbar -->
	<jsp:include page="../../navbar.jsp"></jsp:include>

	<!-- Begin page content -->
	<main class="flex-shrink-0">
		<div class="container">

			<div
				class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}"
				role="alert">
				${errorMessage}
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>

			<div class='card'>
				<div class='card-header'>
					<h5>Ricerca elementi</h5>
				</div>
				<div class='card-body'>

					<form method="post"
						action="${pageContext.request.contextPath}/admin/utente/list"
						class="row g-3">

						<div class="col-md-6">
							<label for="username" class="form-label">Username</label> <input
								type="text" class="form-control" name="username" id="username"
								placeholder="Inserire username">
						</div>
						<div class="col-md-6">
							<label for="dipendente.nome" class="form-label">Nome</label> <input
								type="text" class="form-control" name="dipendente.nome" id="dipendente.nome"
								placeholder="Inserire nome">
						</div>
						<div class="col-md-6">
							<label for="cognome" class="form-label">Cognome</label> <input
								type="text" class="form-control" name="cognome" id="cognome"
								placeholder="Inserire cognome">
						</div>
						<div class="col-md-6">
							<label for="dateCreated" class="form-label">Data di
								Creazione</label> <input class="form-control" id="dateCreated"
								type="date" placeholder="dd/MM/yy" title="formato : gg/mm/aaaa"
								name="dateCreated">
						</div>

						<div class="col-md-3">
							<label for="stato" class="form-label">Stato</label> <select
								class="form-select " id="stato" name="stato">
								<option value="" selected>- Selezionare -</option>
								<option value="ATTIVO">ATTIVO</option>
								<option value="CREATO">CREATO</option>
								<option value="DISABILITATO">DISABILITATO</option>
							</select>
						</div>

						<%--  checkbox ruoli --%>
						<%-- facendolo con i tag di spring purtroppo viene un po' spaginato quindi aggiungo class 'a mano' --%>
						<div class="col-md-6 form-check" id="ruoliDivId">
							<p>Ruoli:</p>
							<c:forEach var="ruoloItem" items="${ruoli_totali_attr}">
								<div class='form-check'>
									<input name="ruoliIds" class="form-check-input" type="checkbox"
										value="${ruoloItem.id }"
										id="flexCheckDefault-${ruoloItem.id }"> <label
										class="form-check-label"
										for="flexCheckDefault-${ruoloItem.id }">
										${ruoloItem.codice } </label>
								</div>
							</c:forEach>
						</div>
						<%-- fine checkbox ruoli --%>

						<div class="col-12">
							<button type="submit" name="submit" value="submit" id="submit"
								class="btn btn-primary">Conferma</button>
							<input class="btn btn-outline-warning" type="reset"
								value="Ripulisci"> <a
								href="${pageContext.request.contextPath}/home"
								class='btn btn-outline-secondary'> <i
								class='fa fa-chevron-left'></i> Torna alla Home
							</a>
						</div>


					</form>

					<!-- end card-body -->
				</div>
			</div>

		</div>
		<!-- end container -->
	</main>
	<jsp:include page="../../footer.jsp" />

</body>
</html>