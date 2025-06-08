<template>
  <nav class="navbar navbar-expand custom-navbar">
    <div class="container-fluid">

      <router-link to="/home" class="navbar-brand d-flex align-items-center">
        <i class="bi bi-globe2 me-2 fs-4"></i>Coco's Tours
      </router-link>

    
      <div class="navbar-nav ms-auto">
        <router-link to="/users" class="nav-link" active-class="active">
          <i class="bi bi-people me-1"></i>Usuarios
        </router-link>
        <router-link to="/reservas" class="nav-link" active-class="active">
          <i class="bi bi-calendar-check me-1"></i>Reservas
        </router-link>
        <router-link to="/tours" class="nav-link" active-class="active">
          <i class="bi bi-map me-1"></i>Tours disponibles
        </router-link>
        <router-link to="/extras" class="nav-link" active-class="active">
          <i class="bi bi-gift me-1"></i>Extras ofrecidos
        </router-link>
        <router-link to="/contacto" class="nav-link" active-class="active">
          <i class="bi bi-envelope me-1"></i>Contáctenos
        </router-link>
      </div>

    </div>
  </nav>

  <router-view />

  <footer class="footer py-3 mt-4">
    <p class="mb-0">
      <i class="bi bi-c-circle me-1"></i>Coco's Tours Web Application &reg; All rights reserved
    </p>
  </footer>
</template>