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

        <!-- Reservas -->
        <div class="nav-item dropdown">
          <a
            class="nav-link dropdown-toggle"
            href="#"
            role="button"
            data-bs-toggle="dropdown"
            @click.prevent
          >
            <i class="bi bi-calendar-check me-1"></i>Reservas
          </a>
          <ul class="dropdown-menu dropdown-menu-dark">
            <li><router-link to="/reservation" class="dropdown-item">Listar</router-link></li>
            <li><router-link to="/reservations/add" class="dropdown-item">Agregar</router-link></li>
          </ul>
        </div>

        <!-- Tours -->
        <div class="nav-item dropdown">
          <a
            class="nav-link dropdown-toggle"
            href="#"
            role="button"
            data-bs-toggle="dropdown"
            @click.prevent
          >
            <i class="bi bi-map me-1"></i>Tours disponibles
          </a>
          <ul class="dropdown-menu dropdown-menu-dark">
            <li><router-link to="/tours" class="dropdown-item">Listar</router-link></li>
            <li><router-link to="/tours/add" class="dropdown-item">Agregar</router-link></li>
          </ul>
        </div>

        <!-- Extras -->
        <div class="nav-item dropdown">
          <a
            class="nav-link dropdown-toggle"
            href="#"
            role="button"
            data-bs-toggle="dropdown"
            @click.prevent
          >
            <i class="bi bi-gift me-1"></i>Extras ofrecidos
          </a>
          <ul class="dropdown-menu dropdown-menu-dark">
            <li><router-link to="/extras" class="dropdown-item">Listar</router-link></li>
            <li><router-link to="/extras/add" class="dropdown-item">Agregar</router-link></li>
          </ul>
        </div>

        <!-- Contacto -->
        <router-link to="/contacto" class="nav-link" active-class="active">
          <i class="bi bi-envelope me-1"></i>Contáctenos
        </router-link>

        <!-- Logout -->
        <button @click="logout" class="btn btn-outline-danger ms-3">
          <i class="bi bi-box-arrow-right me-1"></i>Cerrar sesión
        </button>
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

<script setup lang="ts">
import { useRouter } from 'vue-router'
import { useAuth } from '@/services/auth.service'

const router = useRouter()
const { logout: logoutService } = useAuth()

const logout = () => {
  logoutService()
  router.push('/login')
}
</script>