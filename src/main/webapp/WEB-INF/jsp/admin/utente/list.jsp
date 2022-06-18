<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html lang="it" class="h-100">
<head>

<!-- Common imports in pages -->
<jsp:include page="../../header.jsp" />

<title>Pagina dei Risultati</title>
</head>

<body class="d-flex flex-column h-100">

	<div class="modal fade" id="abilitamodal" tabindex="-1" role="dialog"
		aria-labelledby="abilitamodal" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Confermare l'abilitazione dell'utente?</h5>
				</div>
				<div class="modal-footer">
					<button type="button" id="closeabilita" class="btn btn-secondary"
						data-dismiss="modal">Annulla</button>
					<button type="button" id="confermaabilita" class="btn btn-primary">Conferma</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="disabilitamodal" tabindex="-1" role="dialog"
		aria-labelledby="disabilitamodal" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Confermare la disabilitazione dell'utente?</h5>
				</div>
				<div class="modal-footer">
					<button type="button" id="closedisabilita" class="btn btn-secondary"
						data-dismiss="modal">Annulla</button>
					<button type="button" id="confermadisabilita" class="btn btn-primary">Conferma</button>
				</div>
			</div>
		</div>
	</div>


	<!-- Fixed navbar -->
	<jsp:include page="../../navbar.jsp"></jsp:include>


	<!-- Begin page content -->
	<main class="flex-shrink-0">
		<div class="container">

			<div
				class="alert alert-success alert-dismissible fade show  ${successMessage==null?'d-none':'' }"
				role="alert">
				${successMessage}
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>
			<div
				class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }"
				role="alert">
				${errorMessage}
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>

			<div class='card'>
				<div class='card-header'>
					<h5>Lista utenti</h5>
				</div>
				<div class='card-body'>
					<div class='table-responsive'>
						<table class='table table-striped '>
							<thead>
								<tr>
									<th>Username</th>
									<th>Stato</th>
									<th>Azioni</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${utenti_list_attribute }" var="utenteItem">
									<tr>
										<td>${utenteItem.username }</td>
										<td>${utenteItem.stato }</td>
										<td><a class="btn  btn-sm btn-outline-secondary"
											href="${pageContext.request.contextPath}/admin/utente/show/${utenteItem.id }">Visualizza</a>
											<a class="btn  btn-sm btn-outline-danger"
											href="${pageContext.request.contextPath}/admin/utente/edit/${utenteItem.id }">Modifica
												Permessi</a> 
										<c:if test="${utenteItem.isAttivo()}">
										<a data-toggle="modal" data-utenteId="${utenteItem.id }"
											class="btn disabilita btn-sm btn-outline-warning" >Disabilita
												Utente</a>		
										</c:if>
										<c:if test="${!utenteItem.isAttivo()}">
										<a data-toggle="modal" data-utenteId="${utenteItem.id }"
											class="btn abilita btn-sm btn-outline-success">Abilita
												Utente</a>		
										</c:if>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<script>
						var id = 0;
						$(document).ready(function(){
							  $('.disabilita').click(function(){
								  id = $(this).data('utenteid');
							    $('#disabilitamodal').modal('show');
							   });
							  $('.abilita').click(function(){
								    id = $(this).data('utenteid');
								    $('#abilitamodal').modal('show');
								   });
							  $('#closedisabilita').click(function(){
								    $('#disabilitamodal').modal('hide');
								   });
							  $('#closeabilita').click(function(){
								    $('#abilitamodal').modal('hide');
								   });
							  $('#confermaabilita').click(function(){
								   //$.get("${pageContext.request.contextPath}/admin/utente/toggleabilitation/"+id);
								   window.location.href="${pageContext.request.contextPath}/admin/utente/toggleabilitation/"+id;
							  });
							  $('#confermadisabilita').click(function(){
								   //$.get("${pageContext.request.contextPath}/admin/utente/toggleabilitation/"+id);
								   window.location.href="${pageContext.request.contextPath}/admin/utente/toggleabilitation/"+id;
							  });
							});
						</script>
					</div>
					<!-- end card-body -->
				</div>
				<!-- end card -->
			</div>
			<!-- end container -->
		</div>

	</main>

	<!-- Footer -->
	<jsp:include page="../../footer.jsp" />

</body>
</html>