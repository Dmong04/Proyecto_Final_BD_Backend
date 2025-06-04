<template>
  <nav class="navbar navbar-expand-lg">
    <div class="container-fluid">
      <router-link to="/home">
        <a class="navbar-brand" style="color: #f5f5f5">Coco's Tours</a>
      </router-link>
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarNav"
        aria-controls="navbarNav"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
          <router-link to="/users">
            <li class="nav-item">
              <a class="nav-link">Usuarios</a>
            </li>
          </router-link>
          <li class="nav-item">
            <a class="nav-link">Reservas</a>
          </li>
          <li class="nav-item">
            <a class="nav-link">Tours disponiles</a>
          </li>
          <li class="nav-item">
            <a class="nav-link">Extras ofrecidos</a>
          </li>
          <li class="nav-item">
            <a class="nav-link">Historial de viajes</a>
          </li>
          <li class="nav-item">
            <a class="nav-link">Contáctenos</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <router-view />
  <footer class="footer">
    <p>Coco's tours Web Application &reg; All rights reserved</p>
  </footer>
</template>
