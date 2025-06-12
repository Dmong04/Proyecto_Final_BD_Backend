<template>
  <div class="container d-flex justify-content-center align-items-center">
    <form @submit.prevent="login" class="login-form">
      <div class="content">
        <h1>Inicio de Sesión</h1>
        <h2>Coco's Tours App · Reservas en Línea</h2>
      </div>
      <div class="mb-3">
        <input
          v-model="username"
          type="text"
          id="username"
          class="form-control"
          placeholder="Nombre de usuario"
          required
        />
      </div>
      <div class="mb-3">
        <input
          v-model="password"
          type="password"
          id="password"
          class="form-control"
          placeholder="Contraseña"
          required
        />
      </div>
      <button type="submit" class="btn btn-primary w-100">Ingresar</button>
      <div v-if="error" class="alert alert-danger mt-3">Credenciales inválidas</div>
    </form>
  </div>
</template>

<script setup lang="ts">
import { useAuth } from '@/services/auth.service'
import '@/components/login/login.css'

const { username, password, error, login } = useAuth()
</script>