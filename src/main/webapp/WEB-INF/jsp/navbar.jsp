<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<header>
	<!-- Fixed navbar -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary"
		aria-label="Eighth navbar example">
		<div class="container">
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarsExample07"
				aria-controls="navbarsExample07" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarsExample07">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="${pageContext.request.contextPath}/home">Home</a>
					</li>
					<sec:authorize access="hasRole('ADMIN')">
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="dropdown01"
							data-bs-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false">Gestione Utenze</a>
							<div class="dropdown-menu" aria-labelledby="dropdown01">
								<a class="dropdown-item"
									href="${pageContext.request.contextPath}/admin/dipendente/search">Ricerca
									Dipendenti</a> <a class="dropdown-item"
									href="${pageContext.request.contextPath}/admin/utente/search">Ricerca
									Utenti</a>
							</div></li>
					</sec:authorize>
					<sec:authorize access="hasRole('BO_USER')">
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="dropdown01"
							data-bs-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false">Gestione Dipendenti</a>
							<div class="dropdown-menu" aria-labelledby="dropdown01">
								<a class="dropdown-item"
									href="${pageContext.request.contextPath}/backoffice/dipendente/search">Ricerca
									Dipendenti</a> <a class="dropdown-item"
									href="${pageContext.request.contextPath}/backoffice/dipendente/insert">Inserimento
									Dipendente</a>
							</div></li>
					</sec:authorize>
				</ul>
			</div>
			<sec:authorize access="isAuthenticated()">

				<div class="col-md-3 text-end "></div>
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<p class="navbar-text">Utente: ${userInfo.username }
						(${userInfo.getDipendenteDTO().nome }
						${userInfo.getDipendenteDTO().cognome })
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle active" href="#" id="dropdown01"
						data-bs-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false">Account</a>
						<div class="dropdown-menu " aria-labelledby="dropdown01">
							<a class="dropdown-item"
								href="${pageContext.request.contextPath}/logout">Logout</a> <a
								class="dropdown-item"
								href="${pageContext.request.contextPath}/reset">Reset
								Password</a>
						</div></li>
				</ul>
			</sec:authorize>
		</div>
	</nav>


</header>
