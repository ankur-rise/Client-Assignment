package com.llm

import com.google.gson.Gson
import com.llm.data.models.DeliveryItemDataModel


const val DATA = "[{\"id\":40,\"description\":\"Deliver pets to Alan\",\"imageUrl\":\"https://s3-ap-southeast-1.amazonaws.com/lalamove-mock-api/images/pet-1.jpeg\",\"location\":{\"lat\":22.319181,\"lng\":114.170008,\"address\":\"Mong Kok\"}},{\"id\":41,\"description\":\"Deliver parcel to Leviero\",\"imageUrl\":\"https://s3-ap-southeast-1.amazonaws.com/lalamove-mock-api/images/pet-2.jpeg\",\"location\":{\"lat\":22.336093,\"lng\":114.155288,\"address\":\"Cheung Sha Wan\"}},{\"id\":42,\"description\":\"Deliver documents to Andrio\",\"imageUrl\":\"https://s3-ap-southeast-1.amazonaws.com/lalamove-mock-api/images/pet-2.jpeg\",\"location\":{\"lat\":22.336093,\"lng\":114.155288,\"address\":\"Cheung Sha Wan\"}},{\"id\":43,\"description\":\"Deliver wine to Kenneth\",\"imageUrl\":\"https://s3-ap-southeast-1.amazonaws.com/lalamove-mock-api/images/pet-1.jpeg\",\"location\":{\"lat\":22.335538,\"lng\":114.176169,\"address\":\"Kowloon Tong\"}},{\"id\":44,\"description\":\"Deliver wine to Kenneth\",\"imageUrl\":\"https://s3-ap-southeast-1.amazonaws.com/lalamove-mock-api/images/pet-0.jpeg\",\"location\":{\"lat\":22.319181,\"lng\":114.170008,\"address\":\"Mong Kok\"}},{\"id\":45,\"description\":\"Deliver documents to Andrio\",\"imageUrl\":\"https://s3-ap-southeast-1.amazonaws.com/lalamove-mock-api/images/pet-1.jpeg\",\"location\":{\"lat\":22.336093,\"lng\":114.155288,\"address\":\"Cheung Sha Wan\"}},{\"id\":46,\"description\":\"Deliver wine to Kenneth\",\"imageUrl\":\"https://s3-ap-southeast-1.amazonaws.com/lalamove-mock-api/images/pet-5.jpeg\",\"location\":{\"lat\":22.335538,\"lng\":114.176169,\"address\":\"Kowloon Tong\"}},{\"id\":47,\"description\":\"Deliver documents to Andrio\",\"imageUrl\":\"https://s3-ap-southeast-1.amazonaws.com/lalamove-mock-api/images/pet-1.jpeg\",\"location\":{\"lat\":22.335538,\"lng\":114.176169,\"address\":\"Kowloon Tong\"}},{\"id\":48,\"description\":\"Deliver documents to Andrio\",\"imageUrl\":\"https://s3-ap-southeast-1.amazonaws.com/lalamove-mock-api/images/pet-7.jpeg\",\"location\":{\"lat\":22.336093,\"lng\":114.155288,\"address\":\"Cheung Sha Wan\"}},{\"id\":49,\"description\":\"Deliver wine to Kenneth\",\"imageUrl\":\"https://s3-ap-southeast-1.amazonaws.com/lalamove-mock-api/images/pet-0.jpeg\",\"location\":{\"lat\":22.336093,\"lng\":114.155288,\"address\":\"Cheung Sha Wan\"}},{\"id\":50,\"description\":\"Deliver pets to Alan\",\"imageUrl\":\"https://s3-ap-southeast-1.amazonaws.com/lalamove-mock-api/images/pet-4.jpeg\",\"location\":{\"lat\":22.319181,\"lng\":114.170008,\"address\":\"Mong Kok\"}},{\"id\":51,\"description\":\"Deliver documents to Andrio\",\"imageUrl\":\"https://s3-ap-southeast-1.amazonaws.com/lalamove-mock-api/images/pet-6.jpeg\",\"location\":{\"lat\":22.336093,\"lng\":114.155288,\"address\":\"Cheung Sha Wan\"}},{\"id\":52,\"description\":\"Deliver parcel to Leviero\",\"imageUrl\":\"https://s3-ap-southeast-1.amazonaws.com/lalamove-mock-api/images/pet-2.jpeg\",\"location\":{\"lat\":22.335538,\"lng\":114.176169,\"address\":\"Kowloon Tong\"}},{\"id\":53,\"description\":\"Deliver wine to Kenneth\",\"imageUrl\":\"https://s3-ap-southeast-1.amazonaws.com/lalamove-mock-api/images/pet-2.jpeg\",\"location\":{\"lat\":22.335538,\"lng\":114.176169,\"address\":\"Kowloon Tong\"}},{\"id\":54,\"description\":\"Deliver documents to Andrio\",\"imageUrl\":\"https://s3-ap-southeast-1.amazonaws.com/lalamove-mock-api/images/pet-8.jpeg\",\"location\":{\"lat\":22.336093,\"lng\":114.155288,\"address\":\"Cheung Sha Wan\"}},{\"id\":55,\"description\":\"Deliver food to Eric\",\"imageUrl\":\"https://s3-ap-southeast-1.amazonaws.com/lalamove-mock-api/images/pet-8.jpeg\",\"location\":{\"lat\":22.319181,\"lng\":114.170008,\"address\":\"Mong Kok\"}},{\"id\":56,\"description\":\"Deliver pets to Alan\",\"imageUrl\":\"https://s3-ap-southeast-1.amazonaws.com/lalamove-mock-api/images/pet-7.jpeg\",\"location\":{\"lat\":22.319181,\"lng\":114.170008,\"address\":\"Mong Kok\"}},{\"id\":57,\"description\":\"Deliver documents to Andrio\",\"imageUrl\":\"https://s3-ap-southeast-1.amazonaws.com/lalamove-mock-api/images/pet-3.jpeg\",\"location\":{\"lat\":22.335538,\"lng\":114.176169,\"address\":\"Kowloon Tong\"}},{\"id\":58,\"description\":\"Deliver wine to Kenneth\",\"imageUrl\":\"https://s3-ap-southeast-1.amazonaws.com/lalamove-mock-api/images/pet-0.jpeg\",\"location\":{\"lat\":22.336093,\"lng\":114.155288,\"address\":\"Cheung ShaWan\"}},{\"id\":59,\"description\":\"Deliver food to Eric\",\"imageUrl\":\"https://s3-ap-southeast-1.amazonaws.com/lalamove-mock-api/images/pet-1.jpeg\",\"location\":{\"lat\":22.335538,\"lng\":114.176169,\"address\":\"Kowloon Tong\"}}]"



fun parseJson():List<DeliveryItemDataModel> {
    val gson = Gson()
    val array =  gson.fromJson(DATA, Array<DeliveryItemDataModel>::class.java).toList()
    return array
}