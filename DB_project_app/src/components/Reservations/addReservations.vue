<template>
  <div class="container mt-5">
    <div class="card shadow-lg border-0 rounded-4">
      <div class="card-header custom-header">
        <h4 class="mb-0"><i class="bi bi-file-earmark-plus"></i>Agregar Reserva</h4>
      </div>

      <div class="card-body">
        <form>
          <div class="details row col-md-7 mb-3">
            <form @submit.prevent="submitTourDetail">
              <div class="input-group col-md-1">
                <span>Detalles del viaje</span><br />
                <div class="input-group col-md-6 mb-3">
                  <input
                    type="text"
                    id="numPassengers"
                    class="form-control"
                    placeholder="Número de Pasajeros"
                    v-model="numPassengers"
                    required
                  />
                </div>
                <div class="input-group col-md-6 mb-3">
                  <input
                    type="text"
                    id="origin"
                    class="form-control"
                    placeholder="Lugar de salida del tour"
                    v-model="origin"
                    required
                  />
                </div>
                <div class="input-group col-md-6 mb-3">
                  <input
                    type="text"
                    id="destination"
                    class="form-control"
                    placeholder="Lugar de destino del tour"
                    v-model="destination"
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
                <!-- <label for="id-provider">Selecciona un proveedor:</label>
                <div class="input-group col-md-6 mb-3">
                  <select
                    id="id-provider"
                    class="form-select"
                    v-model="selectedProviderId"
                    :disabled="loading"
                  >
                    <option disabled value="">-- Selecciona un proveedor --</option>
                    <option v-for="provider in providers" :key="provider.id" :value="provider.id">
                      {{ provider.name }}
                    </option>
                  </select>
                  <div v-if="error" class="text-danger mt-2">Error al cargar proveedores.</div>
                </div> -->
                <button type="submit" class="btn btn-dark btn-sm">
                  <i class="bi bi-save me-2"></i>Guardar detalles
                </button>
              </div>
            </form>            


            <form @submit.prevent="submitPassegers">
              <div class="input-group col-m-1 mt-5">
                <span>Pasajeros</span><br />
                <div class="input-group col-md-6 mb-3">
                     <input 
                        type="text" 
                        id="name" 
                        class="form-control" 
                        placeholder="Nombre" 
                        v-model="name"
                        required 
                      />
                    </div>
                    <div class="input-group col-md-6 mb-3">
                      <input 
                        type="text" 
                        id="age" 
                        class="form-control" 
                        placeholder="Edad" 
                        v-model="age"
                        required 
                      />
                    </div>
                    <label for="id-tourDetail">Selecciona el detalle del tour:</label>
                    <div class="input-group col-md-6 mb-3">
                      <select
                        id="id-tourDetail"
                        class="form-select"
                        v-model="selectedTourDetailId"
                        :disabled="tour_detailLoading"
                      >
                        <option disabled value="">-- Selecciona el detalle del tour --</option>
                        <option v-for="tour_detail in tour_details" :key="tour_detail.id" :value="tour_detail.id">
                          {{ tour_detail.origin }}
                        </option>
                      </select>
                      <div v-if="tour_detailError" class="text-danger mt-2">Error al cargar detalles del tour.</div>
                    </div>
                    <button type="submit" class="btn btn-dark btn-sm">
                      <i class="bi bi-save me-2"></i>Guardar pasajero
                    </button>                  
              </div>
            </form>

            
            <form @submit.prevent="submitExtraDetail">
              <div class="input-group col-m-1 mt-5">
                <span>Detalles extra</span><br />
                    <div class="input-group col-md-6 mb-3">
                      <input 
                        type="number" 
                        id="participants" 
                        class="form-control" 
                        placeholder="Participantes" 
                        v-model="participants"
                        required 
                      />
                    </div>
                    <div class="input-group col-md-6 mb-3">
                      <input 
                        type="number" 
                        id="price" 
                        class="form-control" 
                        placeholder="Precio" 
                        v-model="price"
                        required 
                      />
                    </div>
                    <label for="id-extra">Selecciona el extra:</label>
                    <div class="input-group col-md-6 mb-3">
                      <select
                        id="id-extra"
                        class="form-select"
                        v-model="selectedExtraId"
                        :disabled="extraLoading"
                      >
                        <option disabled value="">-- Selecciona el extra --</option>
                        <option v-for="extra in extras" :key="extra.id" :value="extra.id">
                          {{ extra.name }}
                        </option>
                      </select>
                      <div v-if="extraError" class="text-danger mt-2">Error al cargar detalles del extra.</div>
                    </div>
                    <button type="submit" class="btn btn-dark btn-sm">
                      <i class="bi bi-save me-2"></i>Guardar detalles extra
                    </button>                  
              </div>            
            </form>


            <form @submit.prevent="submitReservation">
              <div class="row mt-5">
                <span>Reservación</span><br />
                <div class="col-md-6 mb-3">
                  <label for="fecha" class="form-label">Fecha</label>
                  <div class="input-group">
                    <span class="input-group-text">
                      <i class="bi bi-calendar-event"></i>
                    </span>
                    <input type="date" id="fecha" class="form-control" v-model="fecha" required />
                  </div>
                </div>
                <div class="col-md-6 mb-3">
                  <label for="hora" class="form-label">Hora</label>
                  <div class="input-group">
                    <span class="input-group-text">
                      <i class="bi bi-clock"></i>
                    </span>
                    <input type="time" id="hora" class="form-control" v-model="hora" required />
                  </div>
                </div>
              </div>

              <div class="mb-3">
                <label for="descripcion" class="form-label">Descripción</label>
                <div class="input-group">
                  <span class="input-group-text">
                    <i class="bi bi-pencil-square"></i>
                  </span>
                  <input type="text" id="descripcion" class="form-control" v-model="descripcion" required />
                </div>
              </div>

              <div class="row">
                <div class="col-md-6 mb-3">
                  <label for="precioTour" class="form-label">Precio del tour</label>
                  <div class="input-group">
                    <span class="input-group-text">
                      <i class="bi bi-ticket-detailed"></i>
                    </span>
                    <input type="text" id="precioTour" class="form-control" v-model="precioTour" required />
                  </div>
                </div>

                <div class="col-md-6 mb-3">
                  <label for="precioExtra" class="form-label">Precio del extra</label>
                  <div class="input-group">
                    <span class="input-group-text">
                      <i class="bi bi-person-circle"></i>
                    </span>
                    <input type="text" id="precioExtra" class="form-control" v-model="precioExtra" required />
                  </div>
                </div>
              </div>

              <div class="row">
                <div class="col-md-6 mb-3">
                  <label for="total" class="form-label">Total</label>
                  <div class="input-group">
                    <span class="input-group-text">
                      <i class="bi bi-ticket-detailed"></i>
                    </span>
                    <input type="text" id="total" class="form-control" v-model="total" required />
                  </div>
                </div>

                <div class="col-md-6 mb-3">
                  <label for="detalleExtra" class="form-label">ID Detalle Extra</label>
                  <div class="input-group">
                    <span class="input-group-text">
                      <i class="bi bi-person-circle"></i>
                    </span>
                    <select
                      id="detalleExtra"
                      class="form-select"
                      v-model="selectedExtraDetailId"
                      :disabled="extra_detailLoading"
                    >
                      <option disabled value="">-- Selecciona el detalle del extra --</option>
                      <option v-for="extra_detail in extra_details" :key="extra_detail.id" :value="extra_detail.id">
                        {{ extra_detail.participants }}
                      </option>
                    </select>
                    <div v-if="extra_detailError" class="text-danger mt-2">Error al cargar detalles del extra.</div>
                  </div>
                </div>
              </div>

              <div class="row">
                <div class="col-md-6 mb-3">
                  <label for="detalleViaje" class="form-label">ID Detalle Viaje</label>
                  <div class="input-group">
                    <span class="input-group-text">
                      <i class="bi bi-ticket-detailed"></i>
                    </span>
                    <select
                      id="detalleViaje"
                      class="form-select"
                      v-model="selectedTourDetailId2"
                      :disabled="tour_detailLoading"
                    >
                      <option disabled value="">-- Selecciona el detalle del viaje--</option>
                      <option v-for="tour_detail in tour_details" :key="tour_detail.id" :value="tour_detail.id">
                        {{ tour_detail.origin }}
                      </option>
                    </select>
                    <div v-if="tour_detailError" class="text-danger mt-2">Error al cargar detalles del viaje.</div>
                  </div>
                </div>

                <div class="col-md-6 mb-3">
                  <label for="usuario" class="form-label">ID Usuario</label>
                  <div class="input-group">
                    <span class="input-group-text">
                      <i class="bi bi-person-circle"></i>
                    </span>
                    <select
                      id="usuario"
                      class="form-select"
                      v-model="selectedUserId"
                      :disabled="userLoading"
                    >
                      <option disabled value="">-- Selecciona el usuario--</option>
                      <option v-for="user in users" :key="user.id" :value="user.id">
                        {{ user.username }}
                      </option>
                    </select>
                    <div v-if="userError" class="text-danger mt-2">Error al cargar usuarios.</div>
                  </div>
                </div>
              </div>

              <div class="d-flex justify-content-center mt-4">
                <button type="submit" class="btn btn-dark btn-sm">
                  <i class="bi bi-save me-2"></i>Guardar Reserva
                </button>
              </div>
            </form>
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
import { ExtraComponet } from '../Extra/extra.component'
import { UserComponent } from '../Users/user.component'
import { ReservationComponent } from './reservation.component'
import { TourDetailComponent } from './reservation.component'
import { ExtraDetailComponent } from './reservation.component'
import reservationService from '@/services/reservation.service'
import type { Reservation } from '@/models/reservation'
import type { Passenger } from '@/models/passenger'
import passengerService from '@/services/passenger.service'
import { TourDetail } from '@/models/tour_detail'
import tour_detailService from '@/services/tour_detail.service'
import type { ExtraDetail } from '@/models/extra_detail'
import extra_detailService from '@/services/extra_detail.service'

const { tours, loading, error } = TourComponent()
const { extras, loading: extraLoading, error:extraError } = ExtraComponet()
const { reservations, loading: reservationLoading, error:reservationError } = ReservationComponent()
const { tour_details, loading: tour_detailLoading, error:tour_detailError } = TourDetailComponent()
const { extra_details, loading: extra_detailLoading, error:extra_detailError } = ExtraDetailComponent()
const { users, loading: userLoading, error:userError } = UserComponent()

const numPassengers = ref('')
const origin = ref('')
const destination = ref('')
const selectedTourId = ref<number | null>(null)
const selectedProviderId = ref<number | null>(null)

const submitTourDetail = async () => {
  const newTourDetail = {
    numPassengers: numPassengers.value,
    origin: origin.value,
    destination: destination.value,
    tour: selectedTourId.value 
    // y el proveedor ???
  }
  console.log('Detalle enviado:', newTourDetail)
  try {
    await extra_detailService.createExtraDetail(newExtraDetail)
    alert('Tour guardado con éxito')
    // limpiar campos si quieres
    participants.value = ''
    price.value = ''
    selectedTourDetailId.value = null
  } catch (error) {
    console.error('Error guardando tour:', error)
    alert('Error al guardar el tour')
  }   
}

const name = ref('')
const age = ref('')
const selectedTourDetailId = ref<number | null>(null)

const submitPassegers = async () => {
  const newPasseger = {
    name: name.value,
    age: age.value,
    tourDetail: selectedTourDetailId.value
  }

  console.log('Pasajero enviado:', newPasseger)
  //POST
  try {
    await passengerService.createPassenger(newPasseger)
    alert('Tour guardado con éxito')
    // limpiar campos si quieres
    name.value = ''
    age.value = ''
    selectedTourDetailId.value = null
  } catch (error) {
    console.error('Error guardando tour:', error)
    alert('Error al guardar el tour')
  }  
}

const participants = ref('')
const price = ref('')
const selectedExtraId = ref<number | null>(null)

const submitExtraDetail = async () => {
  const newExtraDetail = {
    participants: participants.value,
    price: price.value,
    extra: selectedExtraId.value
  }
  console.log('Detalle extra enviado:', newExtraDetail)
  try {
    await extra_detailService.createExtraDetail(newExtraDetail)
    alert('Tour guardado con éxito')
    // limpiar campos si quieres
    participants.value = ''
    price.value = ''
    selectedTourDetailId.value = null
  } catch (error) {
    console.error('Error guardando tour:', error)
    alert('Error al guardar el tour')
  }  
  
}

const fecha = ref('')
const hora = ref('')
const descripcion = ref('')
const precioTour = ref('')
const precioExtra = ref('')
const total = ref('')
const selectedExtraDetailId = ref<number | null>(null)
const selectedTourDetailId2 = ref<number | null>(null)
const selectedUserId = ref<number | null>(null)

const submitReservation = async () => {
  const newReservation = {
    fecha: fecha.value,
    hora: hora.value,
    descripcion: descripcion.value,
    precioTour: precioTour.value,
    precioExtra: precioExtra.value,
    total: total.value,
    detailExtra: selectedExtraDetailId.value,
    detailTour: selectedTourDetailId2.value,
    user: selectedUserId
  }
  console.log('Detalle extra enviado:', newReservation)
  try {
    await extra_detailService.createExtraDetail(newExtraDetail)
    alert('Tour guardado con éxito')
    // limpiar campos si quieres
    participants.value = ''
    price.value = ''
    selectedTourDetailId.value = null
  } catch (error) {
    console.error('Error guardando tour:', error)
    alert('Error al guardar el tour')
  }   
}

</script>
