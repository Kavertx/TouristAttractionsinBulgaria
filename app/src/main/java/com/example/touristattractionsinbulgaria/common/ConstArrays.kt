package com.example.touristattractionsinbulgaria.common

//TODO:create and fill arrays of district names and attraction names


//This might be a good place to use an enum instead

//val Districts = arrayOf(
//    "Blagoevgrad Province",
//    "Burgas Province",
//    "Varna Province",
//    "Veliko Tarnovo Province",
//    "Vidin Province",
//    "Vratsa Province",
//    "Gabrovo Province",
//    "Dobrich Province",
//    "Kardzhali Province",
//    "Kyustendil Province",
//    "Lovech Province",
//    "Montana Province",
//    "Pazardzhik Province",
//    "Pernik Province",
//    "Pleven Province",
//    "Plovdiv Province",
//    "Razgrad Province",
//    "Ruse Province",
//    "Silistra Province",
//    "Sliven Province",
//    "Smolyan Province",
//    "Sofia Province",
//    "Sofia City Province",
//    "Stara Zagora Province",
//    "Targovishte Province",
//    "Haskovo Province",
//    "Shumen Province",
//    "Yambol Province"
//)

val AttractionDistrictMap = hashMapOf<String, String>(
    "Rila Monastery" to "Blagoevgrad Province",
    "Bansko" to "Blagoevgrad Province",
    "Melnik, Bulgaria" to "Blagoevgrad Province",

    "Sunny Beach" to "Burgas Province",
    "Nessebar (UNESCO World Heritage Site)" to "Burgas Province",
    "Sozopol" to "Burgas Province",

    "Golden Sands" to "Varna Province",
    "Varna Archaeological Museum" to "Varna Province",
    "Aladzha Monastery" to "Varna Province",

    "Tsarevets Fortress" to "Veliko Tarnovo Province",
    "Arbanasi" to "Veliko Tarnovo Province",
    "Patriarchal Cathedral of the Holy Ascension of God" to "Veliko Tarnovo Province",

    "Belogradchik Rocks" to "Vidin Province",
    "Baba Vida " to "Vidin Province",
    "Magura Cave" to "Vidin Province",

    "Vrachanski Balkan Nature Park" to "Vratsa Province",
    "Ledenika" to "Vratsa Province",

    "Etar Architectural-Ethnographic Complex" to "Gabrovo Province",
    "Sokolski Monastery" to "Gabrovo Province",
    "Shipka Pass" to "Gabrovo Province",

    "Balchik Palace" to "Dobrich Province",
    "Kaliakra" to "Dobrich Province",

    "Perperikon" to "Kardzhali Province",
    "Tatul" to "Kardzhali Province",
    "Кърджалийски манастир" to "Kardzhali Province",

    "Hisarlaka fortress" to "Kyustendil Province",
    "Osogovo Monastery" to "Kyustendil Province",
    "Rila Monastery" to "Kyustendil Province",
    "Rila Monastery Nature Park" to "Kyustendil Province",

    "Ловешка средновековна крепост" to "Lovech Province",
    "Devetashka cave" to "Lovech Province",
    "Вароша (квартал на Ловеч)" to "Lovech Province",

    "Lopushna Monastery" to "Montana Province",
    "Belogradchik Fortress" to "Montana Province",
    "Chiprovtsi Monastery" to "Montana Province",

    "Batak Reservoir" to "Pazardzhik Province",
    "Tsepina" to "Pazardzhik Province",
    "Perperikon" to "Pazardzhik Province",

    "Кракра (крепост)" to "Pernik Province",
    "Трънско ждрело" to "Pernik Province",
    "Музей на минното дело" to "Pernik Province",

    "Pleven Panorama" to "Pleven Province",
    "Kaylaka" to "Pleven Province",

    "Roman theatre of Philippopolis" to "Plovdiv Province",
    "Old Town (Plovdiv)" to "Plovdiv Province",
    "Bachkovo Monastery" to "Plovdiv Province",

    "Abritus" to "Razgrad Province",
    "Исторически музей (Исперих)" to "Razgrad Province",
    "Сборяново" to "Razgrad Province",

    "Rock-hewn Churches of Ivanovo" to "Ruse Province",
    "Cherven (fortress)" to "Ruse Province",
    "Ruse Opera and Philharmonic Society" to "Ruse Province",

    "Srebarna Nature Reserve" to "Silistra Province",
    "Етнографски музей „Дунавски риболов и лодкостроене“"  to "Silistra Province",
    "Регионален исторически музей (Силистра)" to "Silistra Province",

    "Смолянски езера" to "Smolyan Province",
    "Uhlovitsa" to "Smolyan Province",
    "Rhodope Mountains" to "Smolyan Province",

    "Alexander Nevsky Cathedral, Sofia" to "Sofia City Province",
    "National Palace of Culture" to "Sofia City Province",
    "Vitosha" to "Sofia City Province",

    "Iskar Gorge" to "Sofia Province",
    "Etropole Monastery" to "Sofia Province",
    "Boyana Church" to "Sofia Province",

    "Neolithic Dwellings Museum" to "Stara Zagora Province",
    "Monastery of Saint Athanasius" to "Stara Zagora Province",
    "Shipka Monument" to "Stara Zagora Province",

    "Регионален исторически музей (Търговище)" to "Targovishte Province",
    "Мисионис" to "Targovishte Province",

    "Monument of the Holy Mother of God (Haskovo)" to "Haskovo Province",
    "Thracian tomb of Aleksandrovo" to "Haskovo Province",
    "Villa Armira" to "Haskovo Province",

    "Shumen Fortress" to "Shumen Province",
    "Monument to 1300 Years of Bulgaria, Shumen" to "Shumen Province",
    "Madara Rider" to "Shumen Province",

    "Cabyle" to "Yambol Province",
    "Регионален исторически музей (Ямбол)" to "Yambol Province",
    "Ямболски безистен" to "Yambol Province"
    )
val DistrictsArray = AttractionDistrictMap.values.distinct()
val AttractionArray = AttractionDistrictMap.keys.toList()