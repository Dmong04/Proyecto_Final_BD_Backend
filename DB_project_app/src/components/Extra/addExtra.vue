<template>
  <div class="container mt-5">
    <div class="card shadow-lg border-0 rounded-4">
      <div class="card-header custom-header">
        <h4 class="mb-0">
          <i class="bi bi-patch-plus me-2"></i>Agregar Extra
        </h4>
      </div>
      <div class="card-body">
        <form @submit.prevent="submitExtra">
          <div class="mb-3">
            <label for="nombre" class="form-label">Nombre</label>
            <div class="input-group">
              <span class="input-group-text"><i class="bi bi-tag"></i></span>
              <input v-model="nombre" type="text" id="nombre" class="form-control" required />
            </div>
          </div>

          <div class="mb-3">
            <label for="descripcion" class="form-label">Descripción</label>
            <div class="input-group">
              <span class="input-group-text"><i class="bi bi-card-text"></i></span>
              <textarea v-model="descripcion" id="descripcion" class="form-control" required></textarea>
            </div>
          </div>

          <div class="mb-3">
            <label for="precioPersona" class="form-label">Precio por persona</label>
            <div class="input-group">
              <span class="input-group-text"><i class="bi bi-currency-dollar"></i></span>
              <input v-model.number="precioPersona" type="number" id="precioPersona" class="form-control" required />
            </div>
          </div>
          
          <div class="d-flex justify-content-center mt-4">
            <button type="submit" class="btn btn-dark btn-sm">
                <i class="bi bi-save me-2"></i>Guardar Tour
            </button>
        </div>

        </form>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import extraService from '@/services/extra.service'

const nombre = ref('')
const descripcion = ref('')
const precioPersona = ref<number | null>(null)

const submitExtra = async () => {
  const nuevoExtra = {
    name: nombre.value,
    description: descripcion.value,
    price: precioPersona.value ?? 0
  }

  console.log('Tour enviado:', nuevoExtra)

  try {
    await extraService.createExtra(nuevoExtra)
    alert('Tour guardado con éxito')
    // limpiar campos si quieres
    nombre.value = ''
    descripcion.value = ''
    precioPersona.value = null
  } catch (error) {
    console.error('Error guardando tour:', error)
    alert('Error al guardar el tour')
  }
}
</script>

<style scoped>
.custom-header {
  background-color: #022135;
  color: #ffce54;
  border-top-left-radius: 1rem;
  border-top-right-radius: 1rem;
  padding: 1rem 1.5rem;
}

.card {
  border-radius: 1rem;
  border: none;
}

label {
  font-weight: 500;
  color: #333;
}

.input-group-text {
  background-color: #e9ecef;
  border-radius: 0.5rem 0 0 0.5rem;
}

input.form-control,
textarea.form-control {
  border-radius: 0 0.5rem 0.5rem 0;
}

button.btn {
  font-weight: 600;
  border-radius: 0.5rem;
}
</style>