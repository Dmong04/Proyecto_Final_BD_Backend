<template>
  <div class="container mt-4">
    <h2 class="mb-3">Lista de Usuarios</h2>

    <div v-if="loading" class="alert alert-info">Cargando usuarios...</div>
    <div v-if="error" class="alert alert-danger">Error al cargar usuarios.</div>

    <table v-if="paginatedUsers.length" class="table table-striped">
      <thead class="table-dark">
        <tr>
          <th>ID</th>
          <th>Email</th>
          <th>Nombre de usuario</th>
          <th>Nombre completo</th>
          <th>Acciones</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="user in paginatedUsers" :key="user.id">
          <td>{{ user.id }}</td>
          <td>{{ user.email || '-' }}</td>
          <td>{{ user.username || '-' }}</td>
          <td>{{ user.client?.name || user.admin?.name || '-' }}</td>
          <td>
            <button class="btn btn-sm btn-danger" @click="deleteUser(user.id)">
              Eliminar
            </button>
          </td>
        </tr>
      </tbody>
    </table>

    <nav v-if="pageCount > 1" aria-label="">
      <ul class="pagination justify-content-center">
        <li
          class="page-item"
          :class="{ disabled: currentPage === 1 }"
          @click="changePage(currentPage - 1)"
        >
          <a class="page-link" href="#">Anterior</a>
        </li>
        <li
          v-for="page in pageCount"
          :key="page"
          class="page-item"
          :class="{ active: currentPage === page }"
          @click="changePage(page)"
        >
          <a class="page-link" href="#">{{ page }}</a>
        </li>
        <li
          class="page-item"
          :class="{ disabled: currentPage === pageCount }"
          @click="changePage(currentPage + 1)"
        >
          <a class="page-link" href="#">Siguiente</a>
        </li>
      </ul>
    </nav>
  </div>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue'
import { useRouter } from 'vue-router'
import { UserComponent } from './user.component'

const { users, loading, error, deleteUserById } = UserComponent()
const router = useRouter()

const currentPage = ref(1)
const pageSize = 10

const pageCount = computed(() =>
  Math.ceil((users.value?.length || 0) / pageSize)
)

const paginatedUsers = computed(() => {
  const start = (currentPage.value - 1) * pageSize
  return (users.value || []).slice(start, start + pageSize)
})

function changePage(page: number) {
  if (page < 1) page = 1
  if (page > pageCount.value) page = pageCount.value
  currentPage.value = page
}


async function deleteUser(userId: number) {
  const confirmed = confirm('¿Estás seguro de que deseas eliminar este usuario?')
  if (confirmed) {
    await deleteUserById(userId)
  }
}
</script>
