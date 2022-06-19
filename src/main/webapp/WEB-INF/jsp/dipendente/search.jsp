<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html lang="it" class="h-100" >
<head>
	<jsp:include page="../header.jsp" />
	<title>Ricerca</title>
	
    
</head>
<body class="d-flex flex-column h-100">
	<!-- Fixed navbar -->
	<jsp:include page="../navbar.jsp"></jsp:include>
	
	<!-- Begin page content -->
	<main class="flex-shrink-0">
	  <div class="container">
	
			<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
			  ${errorMessage}
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
			    <span aria-hidden="true">&times;</span>
			  </button>
			</div>
			
			<div class="alert alert-warning alert-dismissible fade show d-none " role="alert">
			  Attenzione!!! Funzionalit� ancora non implementata. Sulla 'Conferma' per il momento parte il 'listAll'
			  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
			</div>
			
			<div class='card'>
			    <div class='card-header'>
			        <h5>Ricerca elementi</h5> 
			    </div>
			    <div class='card-body'>
	
						<form:form method="post" action="${pageContext.request.contextPath}/dipendente/list" class="row g-3">
						
							<div class="col-md-6">
								<label for="nome" class="form-label">Nome</label>
								<input type="text" name="nome" id="nome" class="form-control" placeholder="Inserire il nome" >
							</div>
							
							<div class="col-md-6">
								<label for="cognome" class="form-label">Cognome</label>
								<input type="text" name="cognome" id="cognome" class="form-control" placeholder="Inserire il cognome" >
							</div>
							
							<div class="col-md-6">
								<label for="codiceFiscale" class="form-label">Codice Fiscale</label>
								<input type="text" name="codiceFiscale" id="codiceFiscale" class="form-control" placeholder="Inserire il Codice Fiscale" >
							</div>
							
							<div class="col-md-6">
								<label for="email" class="form-label">Email</label>
								<input type="text" name="email" id="email" class="form-control" placeholder="Inserire l'Email" >
							</div>
							
							<div class="col-md-3">
								<label for="dataNascita" class="form-label">Data di Nascita </label>
                        		<input class="form-control" id="dataNascita" type="date" placeholder="dd/MM/yy"
                            			title="formato : gg/mm/aaaa"  name="dataNascita"   >
							</div>
							
							<div class="col-md-3">
								<label for="dataAssunzione" class="form-label">Data Assunzione </label>
                        		<input class="form-control" id="dataAssunzione" type="date" placeholder="dd/MM/yy"
                            			title="formato : gg/mm/aaaa"  name="dataAssunzione"   >
							</div>
								
							<div class="col-md-3">
								<label for="sesso" class="form-label">Sesso </label>
								<select class="form-select" id="sesso" name="sesso" >
									<option value="" selected> - Selezionare - </option>
								    <option value="MASCHIO" >M</option>
								    <option value="FEMMINA"  >F</option>
								</select>
							</div>
							
							
							<div class="col-12">	
								<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
								<input class="btn btn-outline-warning" type="reset" value="Ripulisci">
							</div>
	
							
						</form:form>
			    
				<!-- end card-body -->			   
			    </div>
			</div>	
	
		</div>
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>             