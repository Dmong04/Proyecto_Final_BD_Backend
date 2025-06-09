<template>

    <div class="container mt-4">

        <h2>Lista de Extras</h2>

        <div v-if="loading" class="alert alert-info">Cargando reservas...</div>
        <div v-if="error" class="alert alert-danger">Error al cargar reservas.</div>


        <nav v-if="pageCount > 1" aria-label="">
             <ul class="pagination justify-content-center">
                <li
                 class="page-item"
                 :class="{ disabled: currentPage === 1 }"
                 @click="changePage(currentPage - 1)"
                >
                 <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Anterior</a>
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

import { ref, computed} from 'vue'
import { ReservationComponent } from './reservation.component';

const { reservations, loading, error } = ReservationComponent()

const currentPage =  ref(1)
const pageSize = 10

const pageCount = computed(() => Math.ceil((reservations.value?.length || 0) / pageSize))

const paginatedUsers = computed(() => {
  const start = (currentPage.value - 1) * pageSize
  return (reservations.value || []).slice(start, start + pageSize)
})

function changePage(page: number) {
  if (page < 1) page = 1
  if (page > pageCount.value) page = pageCount.value
  currentPage.value = page
}

</script>