package com.example.proj1.kotlin

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DiseaseDatabase(context: Context) : SQLiteOpenHelper(context, "disease4.db", null, 5) {


    override fun onCreate(db: SQLiteDatabase) {
        val query = """
            CREATE TABLE diseases (
                id INTEGER PRIMARY KEY,
                name TEXT,
                description TEXT,
                iconImage TEXT,
                ageFilter INTEGER,
                healthConditionFilter INTEGER,
                forbiddenHealthConditionFilter INTEGER
            );
          
            """

        db.execSQL(
            """
              CREATE TABLE health_conditions (
                id INTEGER PRIMARY KEY,
                name TEXT,
                filterValue INTEGER
            ); 
        """.trimIndent()
        )

        db.execSQL(query)
        val insert =
            """    INSERT INTO diseases (id, name, description, iconImage, ageFilter, healthConditionFilter, forbiddenHealthConditionFilter) VALUES 
(1, '인플루엔자', '인플루엔자는 인플루엔자 바이러스(Influenza virus)에 의한 감염병으로 매년 겨울철에 유행하여 고열과 함께 기침 등의 호흡기 증상을 일으키는 질환입니다.', 'https://images.vacgom.co.kr/%E1%84%8B%E1%85%B5%E1%86%AB%E1%84%91%E1%85%B3%E1%86%AF%E1%84%85%E1%85%AE%E1%84%8B%E1%85%A6%E1%86%AB%E1%84%8C%E1%85%A1.svg', 63, 16383,0),
(2, '폐렴구균', '폐렴구균(Streptococcus pneumoniae)은 급성 중이염, 폐렴 및 균혈증, 수막염 등 침습성 감염을 일으키는 주요 원인균 중의 하나이며, 폐렴구균에 의한 침습성 감염은 영아 및 어린 소아와 65세 이상의 고령자에서 발생 빈도가 높습니다.', 'https://images.vacgom.co.kr/%E1%84%91%E1%85%A8%E1%84%85%E1%85%A7%E1%86%B7%E1%84%80%E1%85%AE%E1%84%80%E1%85%B2%E1%86%AB.svg', 62, 16381,0),
(3, '파상풍', '파상풍균(Clostridium tetani)이 생산하는 독소에 의해 유발되는 급성질환으로 파상풍에 이환되면 골격근의 경직과 근육수축이 발생하는 질병입니다.', 'https://images.vacgom.co.kr/%E1%84%83%E1%85%B5%E1%84%91%E1%85%B3%E1%84%90%E1%85%A6%E1%84%85%E1%85%B5%E1%84%8B%E1%85%A1%E3%86%8D%E1%84%91%E1%85%A1%E1%84%89%E1%85%A1%E1%86%BC%E1%84%91%E1%85%AE%E1%86%BC%E3%86%8D%E1%84%87%E1%85%A2%E1%86%A8%E1%84%8B%E1%85%B5%E1%86%AF%E1%84%92%E1%85%A2.svg', 63, 16383,0),
(4, '디프테리아', '디프테리아균(Corynebacterium diphtheriae) 감염 후 발생하는 급성, 독소(toxin) 매개성 호흡기 감염병입니다. 디프테리아는 온대기후 지역에서 상대적으로 발생율이 높으나 전 세계적으로 디프테리아 발생은 매우 드물며, 예방접종으로 국내에서는 1988년 이후부터는 환자가 발생하고 있지 않습니다.', 'https://images.vacgom.co.kr/%E1%84%83%E1%85%B5%E1%84%91%E1%85%B3%E1%84%90%E1%85%A6%E1%84%85%E1%85%B5%E1%84%8B%E1%85%A1%E3%86%8D%E1%84%91%E1%85%A1%E1%84%89%E1%85%A1%E1%86%BC%E1%84%91%E1%85%AE%E1%86%BC%E3%86%8D%E1%84%87%E1%85%A2%E1%86%A8%E1%84%8B%E1%85%B5%E1%86%AF%E1%84%92%E1%85%A2.svg', 63, 16383,0),
(5, '백일해', '백일해는 그람음성간균인(Bordetella pertussis)에 의한 호흡기 감염 질환입니다. 계절에 따른 발병률 차이는 명백히 밝혀진 바 없으나, 여름과 가을에 증가하는 경향을 보이며 전염성이 매우 높아 가족 내 2차 발병률이 80%에 달합니다.', 'https://images.vacgom.co.kr/%E1%84%83%E1%85%B5%E1%84%91%E1%85%B3%E1%84%90%E1%85%A6%E1%84%85%E1%85%B5%E1%84%8B%E1%85%A1%E3%86%8D%E1%84%91%E1%85%A1%E1%84%89%E1%85%A1%E1%86%BC%E1%84%91%E1%85%AE%E1%86%BC%E3%86%8D%E1%84%87%E1%85%A2%E1%86%A8%E1%84%8B%E1%85%B5%E1%86%AF%E1%84%92%E1%85%A2.svg', 63, 16383, 0),
(6, '대상포진', '대상포진은 피부분절을 따라서 수포성 발진이 발생하는 질환으로 수두-대상포진바이러스(Varicella-zoster virus, VZV)의 일차 감염 후 감각신경절에 잠복해 있던 바이러스가 재활성화되어 발생하는 질환입니다.', 'https://images.vacgom.co.kr/%E1%84%83%E1%85%A2%E1%84%89%E1%85%A1%E1%86%BC%E1%84%91%E1%85%A9%E1%84%8C%E1%85%B5%E1%86%AB.svg', 3, 15893, 490),
(7, 'A형간염', 'A형간염은 A형간염 바이러스(Hepatitis A virus, HAV)에 의하여 발생하는 간염으로 환경 및 위생개선과 적절한 예방조치로 예방이 가능합니다. ▶ A형간염은 어떻게 전파되나요? A형간염은 분변-경구 경로로 전파되며, 대부분 사람에서 사람으로 직접적으로 전파되거나 분변에 오염된 물이나 음식물을 섭취함으로써 간접적으로 전파되기도 합니다.', 'https://images.vacgom.co.kr/A%E1%84%92%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%E1%86%AB%E1%84%8B%E1%85%A7%E1%86%B7.svg', 63, 16383, 0),
(8, 'B형간염', 'B형간염 바이러스(Hepatitis B virus, HBV)에 감염되어 간에 염증이 발생하는 질환으로 경과에 따라 급성과 만성으로 구별할 수 있습니다. 급성 B형간염 중 5~10%가 만성 B형간염으로 진행되며 만성 B형간염이 지속되면 간경화증이나 간세포암으로 진행할 수 있습니다. 예방접종으로 국내 B형간염 바이러스 보유자가 많이 감소하였지만 미국 및 유럽의 여러 국가에 비해 아직도 많이 발생하고 있습니다.', 'https://images.vacgom.co.kr/B%E1%84%92%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%E1%86%AB%E1%84%8B%E1%85%A7%E1%86%B7.svg', 63, 16383, 0),
(9, '수두', '수두는 수두-대상포진 바이러스(Varicella-Zoster virus, VZV)에 의한 일차 감염으로 전염력이 매우 강한 급성 감염질환입니다. 급성의 미열로 시작되고 전신적으로 가렵고 발진성 수포가 발생하는 질환입니다.', 'https://images.vacgom.co.kr/%E1%84%89%E1%85%AE%E1%84%83%E1%85%AE.svg', 56, 15893, 490),
(10, '홍역', '홍역은 전 세계적으로 유행하는 급성 발진성 바이러스 질환으로 전염성이 매우 높은 급성 유행성 감염병입니다. 이전에는 소아에서 생명을 위협하는 주요한 질병이었지만 백신이 개발된 이후 그 발생이 현저히 감소하였습니다. 하지만 일부 개발도상국가에서는 아직도 흔히 발생하고 있습니다. 국내에서는 2001년 대유행 이후로는 환자가 급격히 감소하였고, 우리나라는 36개월 이상 토착형 홍역바이러스에 의한 환자발생이 없고, 높은 홍역 예방접종률과 적절한 감시체계 유지', 'https://images.vacgom.co.kr/%E1%84%92%E1%85%A9%E1%86%BC%E1%84%8B%E1%85%A7%E1%86%A8%E3%86%8D%E1%84%8B%E1%85%B2%E1%84%92%E1%85%A2%E1%86%BC%E1%84%89%E1%85%A5%E1%86%BC%E1%84%8B%E1%85%B5%E1%84%92%E1%85%A1%E1%84%89%E1%85%A5%E1%86%AB%E1%84%8B%E1%85%A7%E1%86%B7%E3%86%8D%E1%84%91%E1%85%AE%E1%86%BC%E1%84%8C%E1%85%B5%E1%86%AB.svg', 56, 15893, 490),
(11, '유행성이하선염', '유행성이하선염은 볼거리라고도 하며, 귀 아래의 침샘이 부어오르고 열과 두통이 동반되는 감염성 바이러스 질환입니다.', 'https://images.vacgom.co.kr/%E1%84%92%E1%85%A9%E1%86%BC%E1%84%8B%E1%85%A7%E1%86%A8%E3%86%8D%E1%84%8B%E1%85%B2%E1%84%92%E1%85%A2%E1%86%BC%E1%84%89%E1%85%A5%E1%86%BC%E1%84%8B%E1%85%B5%E1%84%92%E1%85%A1%E1%84%89%E1%85%A5%E1%86%AB%E1%84%8B%E1%85%A7%E1%86%B7%E3%86%8D%E1%84%91%E1%85%AE%E1%86%BC%E1%84%8C%E1%85%B5%E1%86%AB.svg', 56, 15893, 490),
(12, '풍진', '풍진은 발진, 림프절염을 동반하는 급성 바이러스성 질환입니다. 임신 초기의 임신부가 풍진에 감염될 경우 유산을 하거나 태아에게 선천성 기형을 유발할 수 있습니다.', 'https://images.vacgom.co.kr/%E1%84%92%E1%85%A9%E1%86%BC%E1%84%8B%E1%85%A7%E1%86%A8%E3%86%8D%E1%84%8B%E1%85%B2%E1%84%92%E1%85%A2%E1%86%BC%E1%84%89%E1%85%A5%E1%86%BC%E1%84%8B%E1%85%B5%E1%84%92%E1%85%A1%E1%84%89%E1%85%A5%E1%86%AB%E1%84%8B%E1%85%A7%E1%86%B7%E3%86%8D%E1%84%91%E1%85%AE%E1%86%BC%E1%84%8C%E1%85%B5%E1%86%AB.svg', 56, 15893, 490),
(13, '사람유두종바이러스 감염증', '사람유두종바이러스는 생식기 감염을 일으키는 가장 흔한 원인 병원체 중 하나로, 고위험군 HPV 감염과 관련 있는 암으로는 자궁경부암, 질암, 외음부암, 음경암, 항문암, 구강암, 구인두암 등이 있고 저위험군 HPV 감염과 관련 있는 질환으로는 생식기 사마귀, 재발성 호흡기 유두종 등이 있습니다.', 'https://images.vacgom.co.kr/%E1%84%89%E1%85%A1%E1%84%85%E1%85%A1%E1%86%B7%E1%84%8B%E1%85%B2%E1%84%83%E1%85%AE%E1%84%8C%E1%85%A9%E1%86%BC%E1%84%87%E1%85%A1%E1%84%8B%E1%85%B5%E1%84%85%E1%85%A5%E1%84%89%E1%85%B3%E1%84%80%E1%85%A1%E1%86%B7%E1%84%8B%E1%85%A7%E1%86%B7%E1%84%8C%E1%85%B3%E1%86%BC.svg', 32, 16381, 0);

        """
        db.execSQL(insert)

        db.execSQL(
            """
            
            INSERT INTO health_conditions (id, name, filterValue) VALUES
            (1, "당뇨병", 1),
            (2, "만성 심혈관질환", 2),
            (3, "만성 폐질환", 4),
            (4, "만성 신질환", 8),
            (5, "만성 간질환", 16),
            (6, "항암치료중인 고형암", 32),
            (7, "이식 이외 면역 억제제 사용", 64),
            (8, "장기 이식 경험", 128),
            (9, "조혈모 세포이식", 256),
            (10, "무비증", 512),
            (11, "HIV 감염:CD4>=200/mm3", 1024),
            (12, "HIV 감염:CD4<200/mm3", 2048),
            (13, "임신부", 4096),
            (14, "의료기관 종사자", 8192);
        """.trimIndent()
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS diseases")
        db.execSQL("DROP TABLE IF EXISTS health_conditions")
        onCreate(db)
    }
}