<template>
  <form @submit.prevent="login" class="login">
    <div class="mb-3">
      <div class="content mb-5">
        <h1 class="display-4 fw-bold">Inicio de Sesión</h1>
        <h2 class="text-secondary">Coco's Tours App · Reservas en Línea</h2>
      </div>
    </div>
    <div class="mb-3 entries">
      <input
        v-model="username"
        type="text"
        id="username"
        class="form-control"
        placeholder="Nombre de usuario"
        required
      />
    </div>
    <div class="mb-3 entries">
      <input
        v-model="password"
        type="password"
        id="password"
        class="form-control"
        placeholder="Contraseña"
        required
      />
    </div>
    <button type="submit" class="btn btn-primary">Ingresar</button>
    <div v-if="error" class="alert alert-danger mt-3">Credenciales inválidas</div>
  </form>
</template>

<script setup lang="ts">
import { useAuth } from '@/services/auth.service'

const { username, password, error, login } = useAuth()
</script>
