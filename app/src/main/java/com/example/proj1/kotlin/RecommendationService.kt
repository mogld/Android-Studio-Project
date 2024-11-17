package com.example.proj1.kotlin

class RecommendationService(
    private val diseaseRepository: DiseaseRepository
) {
    fun getRecommendation(healthConditionValue: Int, age: Int): List<Disease> {
        val diseases = this.diseaseRepository.getAllDiseases()
        val ageCondition = AgeCondition.getAgeCondition(age)

        return diseases.filter { it ->
            it.forbiddenHealthConditionFilter and healthConditionValue == 0 && it.ageFilter and ageCondition.value != 0
        }
    }
}