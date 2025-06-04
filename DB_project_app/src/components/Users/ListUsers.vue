<template>
  <div class="container mt-4">
    <h2 class="mb-3">Lista de Usuarios</h2>

    <div v-if="loading" class="alert alert-info">Cargando usuarios...</div>
    <div v-if="error" class="alert alert-danger">Error al cargar usuarios.</div>

    <table v-if="users.length" class="table table-bordered table-striped">
      <thead class="table-dark">
        <tr>
          <th>ID</th>
          <th>Email</th>
          <th>Nombre de Usuario</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="user in users" :key="user.id">
          <td>{{ user.id }}</td>
          <td>{{ user.email }}</td>
          <td>{{ user.username }}</td>
          <td>{{ user.client }}</td>
          <td>{{ user.admin }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup lang="ts">
import { ListUsers } from './list-users'

const { users, loading, error } = ListUsers()
</script>
