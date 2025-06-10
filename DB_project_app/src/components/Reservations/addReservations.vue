<template>
  <div class="container mt-5">
    <div class="card shadow-lg border-0 rounded-4">
      <div class="card-header custom-header">
        <h4 class="mb-0"><i class="bi bi-file-earmark-plus"></i>Agregar Reserva</h4>
      </div>

      <div class="card-body">
        <form>
          <div class="details row col-md-7 mb-3">
            <div class="input-group col-md-1">
              <span>Detalles del viaje</span><br />
              <div class="input-group col-md-6 mb-3">
                <input
                  type="text"
                  id="id-detail"
                  class="form-control"
                  placeholder="Número de Pasajeros"
                  required
                />
              </div>
              <div class="input-group col-md-6 mb-3">
                <input
                  type="text"
                  id="origin"
                  class="form-control"
                  placeholder="Lugar de salida del tour"
                  required
                />
              </div>
              <div class="input-group col-md-6 mb-3">
                <input
                  type="text"
                  id="destination"
                  class="form-control"
                  placeholder="Lugar de destino del tour"
                  required
                />
              </div>
              <label for="id-tour">Selecciona un tour:</label>
              <div class="input-group col-md-6 mb-3">
                <select
                  id="id-tour"
                  class="form-select"
                  v-model="selectedTourId"
                  :disabled="loading"
                >
                  <option disabled value="">-- Selecciona un tour --</option>
                  <option v-for="tour in tours" :key="tour.id" :value="tour.id">
                    {{ tour.type }}
                  </option>
                </select>
                <div v-if="error" class="text-danger mt-2">Error al cargar tours.</div>
              </div>
              <button type="submit" class="btn btn-dark btn-sm">
                <i class="bi bi-save me-2"></i>Guardar detalles
              </button>
            </div>
            <div class="passengers-adding mb-3 mt-3">
              <div class="input-group col-md-6 mb-3">
                <input type="text" id="name" class="form-control" placeholder="Nombre" required />
              </div>
              <div class="input-group col-md-6 mb-3">
                <input type="text" id="age" class="form-control" placeholder="Edad" required />
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-md-6 mb-3">
              <label for="fecha" class="form-label">Fecha</label>
              <div class="input-group">
                <span class="input-group-text">
                  <i class="bi bi-calendar-event"></i>
                </span>
                <input type="date" id="fecha" class="form-control" required />
              </div>
            </div>
            <div class="col-md-6 mb-3">
              <label for="hora" class="form-label">Hora</label>
              <div class="input-group">
                <span class="input-group-text">
                  <i class="bi bi-clock"></i>
                </span>
                <input type="time" id="hora" class="form-control" required />
              </div>
            </div>
          </div>

          <div class="mb-3">
            <label for="descripcion" class="form-label">Descripción</label>
            <div class="input-group">
              <span class="input-group-text">
                <i class="bi bi-pencil-square"></i>
              </span>
              <input type="text" id="descripcion" class="form-control" required />
            </div>
          </div>

          <div class="row">
            <div class="col-md-6 mb-3">
              <label for="detalleViaje" class="form-label">ID Detalle Viaje</label>
              <div class="input-group">
                <span class="input-group-text">
                  <i class="bi bi-ticket-detailed"></i>
                </span>
                <input type="text" id="detalleViaje" class="form-control" required />
              </div>
            </div>

            <div class="col-md-6 mb-3">
              <label for="usuario" class="form-label">ID Usuario</label>
              <div class="input-group">
                <span class="input-group-text">
                  <i class="bi bi-person-circle"></i>
                </span>
                <input type="text" id="usuario" class="form-control" required />
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
import { TourComponent } from '@/components/Tours/tour.component'
import { ref } from 'vue'
import type { Tour } from '@/models/tour'

const { tours, loading, error } = TourComponent()
const selectedTourId = ref<number | null>(null)
</script>
