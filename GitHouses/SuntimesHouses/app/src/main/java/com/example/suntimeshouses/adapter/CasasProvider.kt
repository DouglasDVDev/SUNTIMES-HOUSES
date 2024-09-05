/*
 * Copyright (c) Created Douglas Díaz on 13/2/23 11:56
 *               *  Copyright Ⓒ 2023. All rights reserved Ⓒ 2023
 *               *  Last modified: 10/2/23 22:35
 *               *
 *               *  Licensed under Douglas Solutions License, Version 2.0 (the "License"); you may not use this file
 *               *  except in compliance with the License. You may obtain a copy of the License at
 *               * Unless required by applicable law or agreed to in writing, software
 *               *   distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 *               *   either express or implied. See the License for the specific language governing permissions and
 *               *   limitations under the License.
 */

package com.example.suntimeshouses.adapter

import com.example.suntimeshouses.Houses

//Mostramos la lista de las casas que tenemos, con sus características principales
class CasasProvider {
    companion object{
        val listaCasas = listOf<Houses>(
            Houses(
                "Villa Natalia",
                "1800 €/ sem",
                "4 hab",
                "6 - 8 pers max",
                "Villa grande totalmente equipada con todo lo necesario para tus vacaciones",
                "https://img00.rhimg.com/h.23654085.795.500.0.ffffff.4f1690c0.jpg"
            ),
            Houses(
                "Villa Melina",
                "2500 €/ sem",
                "6 hab",
                "10 - 12 pers max",
                "Villa grande totalmente equipada con todo lo necesario para tus vacaciones",
                "https://img00.rhimg.com/h.23811355.795.500.0.ffffff.36e42d65.jpg"
            ),
            Houses(
                "Villa Damián",
                "3000 €/sem",
                "6 hab",
                "10 - 12 pers max",
                "Villa grande totalmente equipada con todo lo necesario para tus vacaciones",
                "https://img00.rhimg.com/h.22139382.795.500.0.ffffff.56915919.jpg"
            ),
            Houses(
                "Villa Aylin",
                "1800 €/sem",
                "4 hab",
                "8 - 10 pers max",
                "Villa grande totalmente equipada con todo lo necesario para tus vacaciones",
                "https://img00.rhimg.com/h.15329711.795.500.0.ffffff.b433db5b.jpg"
            ),
            Houses(
                "Villa Amy",
                "1500 €/sem",
                "2 hab",
                "2- 4 pers max",
                "Apartamento de lujo, totalmente equipado, piscina, etc.",
                "https://img00.rhimg.com/h.23920605.795.500.0.ffffff.2e0b09b0.jpg"
            ),
            Houses(
                "Villa Ania",
                "2100 €/sem",
                "5 hab",
                "8 - 12 pers max",
                "Villa grande, con todas las comodidades: Piscina, barbacoa, etc.",
                "https://img00.rhimg.com/h.20570810.795.500.0.ffffff.19032bef.jpg"
            ),
            Houses(
                "Villa Dugy",
                "2500 €/sem",
                "5 hab",
                "8 - 10 pers max",
                "Villa grande totalmente equipada con todo lo necesario para tus vacaciones",
                "https://img00.rhimg.com/h.23785235.795.500.0.ffffff.9b4646b4.jpg"
            )

        )
    }
}