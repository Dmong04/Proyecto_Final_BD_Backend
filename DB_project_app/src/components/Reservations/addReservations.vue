<template>
  <div class="container mt-5">
    <div class="card shadow-lg border-0 rounded-4">
      <div class="card-header custom-header">
        <h4 class="mb-0"><i class="bi bi-file-earmark-plus"></i>Agregar Reserva</h4>
      </div>

      <div class="card-body">
        <form @submit.prevent="submitReservation">
          <div class="row">
            <div class="col-md-6 mb-3">
              <label for="fecha" class="form-label">Fecha</label>
              <div class="input-group">
                <span class="input-group-text">
                  <i class="bi bi-calendar-event"></i>
                </span>
                <input v-model="fecha" type="date" id="fecha" class="form-control" required />
              </div>
            </div>

            <div class="col-md-6 mb-3">
              <label for="hora" class="form-label">Hora</label>
              <div class="input-group">
                <span class="input-group-text">
                  <i class="bi bi-clock"></i>
                </span>
                <input v-model="hora" type="time" id="hora" class="form-control" required />
              </div>
            </div>
          </div>

          <div class="mb-3">
            <label for="descripcion" class="form-label">Descripción</label>
            <div class="input-group">
              <span class="input-group-text">
                <i class="bi bi-pencil-square"></i>
              </span>
              <input
                v-model="descripcion"
                type="text"
                id="descripcion"
                class="form-control"
                required
              />
            </div>
          </div>

          <div class="row">
            <div class="col-md-6 mb-3">
              <label for="detalleViaje" class="form-label">ID Detalle Viaje</label>
              <div class="input-group">
                <span class="input-group-text">
                  <i class="bi bi-ticket-detailed"></i>
                </span>
                <input
                  v-model.number="idDetalleViaje"
                  type="number"
                  id="detalleViaje"
                  class="form-control"
                  required
                />
              </div>
            </div>

            <div class="col-md-6 mb-3">
              <label for="usuario" class="form-label">ID Usuario</label>
              <div class="input-group">
                <span class="input-group-text">
                  <i class="bi bi-person-circle"></i>
                </span>
                <input
                  v-model.number="idUsuario"
                  type="number"
                  id="usuario"
                  class="form-control"
                  required
                />
              </div>
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

const fecha = ref('')
const hora = ref('')
const descripcion = ref('')
const idDetalleViaje = ref<number | null>(null)
const idUsuario = ref<number | null>(null)

const submitReservation = () => {
  const nuevaReserva = {
    fecha: fecha.value,
    hora: hora.value,
    descripcion: descripcion.value,
    idDetalleViaje: idDetalleViaje.value,
    idUsuario: idUsuario.value,
  }

  console.log('Reserva enviada:', nuevaReserva)
  // POST
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

input.form-control {
  border-radius: 0 0.5rem 0.5rem 0;
}

button.btn {
  font-weight: 600;
  border-radius: 0.5rem;
}
</style>
