/*
 * Copyright (c) Created Douglas Díaz on 10/2/23 19:36
 *               *  Copyright Ⓒ 2023. All rights reserved Ⓒ 2023
 *               *  Last modified: 10/2/23 19:36
 *               *
 *               *  Licensed under Douglas Solutions License, Version 2.0 (the "License"); you may not use this file
 *               *  except in compliance with the License. You may obtain a copy of the License at
 *               * Unless required by applicable law or agreed to in writing, software
 *               *   distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 *               *   either express or implied. See the License for the specific language governing permissions and
 *               *   limitations under the License.
 */

package com.example.suntimeshouses

//Declaramos las variables necesarias para nuestro RecycleView

data class Houses (
    val titulo: String,
    val precio: String,
    val habitaciones: String,
    val personas: String,
    val descripcion: String,
    val foto: String,
    )