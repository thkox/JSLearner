package eu.tkacas.jslearner.data.source.remote

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import eu.tkacas.jslearner.data.model.Course
import eu.tkacas.jslearner.data.model.CourseLevel
import eu.tkacas.jslearner.data.model.Lesson
import eu.tkacas.jslearner.data.model.Question
import eu.tkacas.jslearner.data.model.UserFirestore
import kotlinx.coroutines.tasks.await

class FirestoreDataSource(private val db: FirebaseFirestore) {

    suspend fun getCourses(): List<Course> {
        val result = db.collection("courses").get().await()
        return result.map { document ->
            document.toObject(Course::class.java).copy(id = document.id)
        }
    }

    suspend fun getLessons(courseId: String): List<Lesson> {
        val result = db.collection("courses").document(courseId).collection("lessons").get().await()
        return result.map { document ->
            document.toObject(Lesson::class.java).copy(id = document.id)
        }
    }

    suspend fun getQuestions(courseId: String, lessonId: String): List<Question> {
        val result = db.collection("courses").document(courseId)
            .collection("lessons").document(lessonId)
            .collection("questions").get().await()
        return result.map { document ->
            document.toObject(Question::class.java).copy(id = document.id)
        }
    }

    suspend fun getUserCompletedCourses(userId: String): Map<String, List<String>> {
        val result = db.collection("users").document(userId).collection("done_courses").get().await()
        return result.associate { document ->
            document.id to (document["list_of_completed_lessons"] as List<String>)
        }
    }

    suspend fun getCoursesBasedOnLevel(courseLevel: CourseLevel): List<Course> {
        val result = db.collection("courses")
            .whereEqualTo("level", courseLevel.name)
            .get()
            .await()
        return result.map { document ->
            document.toObject(Course::class.java).copy(id = document.id)
        }
    }

    suspend fun setUserProfile(userId: String, user: UserFirestore) {
        db.collection("users").document(userId).set(user.toMap()).await()
    }

    suspend fun updateUserProfile(userId: String, user: UserFirestore) {
        val userMap = user.toMap().filterValues { it != null }
        db.collection("users").document(userId).update(userMap).await()
    }

    suspend fun getUserProfile(userId: String): UserFirestore? {
        val result = db.collection("users").document(userId).get().await()
        return result.toObject(UserFirestore::class.java)
    }

    suspend fun checkIfProfileCompleted(userId: String): Boolean {
        val result = db.collection("users").document(userId).get().await()
        return result["profile_completed"] as? Boolean ?: false
    }

    suspend fun getCourse(courseId: String): Course {
        return db.collection("courses").document(courseId).get().await().toObject(Course::class.java)!!
    }

    suspend fun getLesson(courseId: String, lessonId: String): Lesson {
        return db.collection("courses").document(courseId)
            .collection("lessons").document(lessonId).get().await()
            .toObject(Lesson::class.java)!!
    }
}