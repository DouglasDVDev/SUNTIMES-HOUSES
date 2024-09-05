/*
 * Copyright (c) Created Douglas Díaz on 10/2/23 20:13
 *               *  Copyright Ⓒ 2023. All rights reserved Ⓒ 2023
 *               *  Last modified: 10/2/23 20:13
 *               *
 *               *  Licensed under Douglas Solutions License, Version 2.0 (the "License"); you may not use this file
 *               *  except in compliance with the License. You may obtain a copy of the License at
 *               * Unless required by applicable law or agreed to in writing, software
 *               *   distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 *               *   either express or implied. See the License for the specific language governing permissions and
 *               *   limitations under the License.
 */

package com.example.suntimeshouses.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.suntimeshouses.Houses
import com.example.suntimeshouses.databinding.ItemCasasBinding

/*Recogemos los datos provenientes del Adapter (CasasAdapter) y lo pintamos en pantalla utilizando
 el layout item_casas*/
class CasasViewHolder (view:View):RecyclerView.ViewHolder(view) {

    val binding = ItemCasasBinding.bind(view)


    fun render(housesModel: Houses, clickContact: (Int) -> Unit){
        binding.idTituloCasa.text = housesModel.titulo
        binding.idPrecioCasa.text = housesModel.precio
        binding.idHabitacionesCasa.text = housesModel.habitaciones
        binding.idPersonasCasa.text = housesModel.personas
        binding.idDescripcionCasa.text = housesModel.descripcion
        Glide.with(binding.idImagenCasa.context).load(housesModel.foto).into(binding.idImagenCasa)
        binding.btnContacto.setOnClickListener{clickContact(adapterPosition)}

    }
}