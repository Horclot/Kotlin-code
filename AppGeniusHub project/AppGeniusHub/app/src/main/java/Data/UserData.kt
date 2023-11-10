// UserData.kt
package Data

import java.io.Serializable

data class UserData(
    var Uname: String = "",
    var Uemail: String = "",
    var Upassword: String = "",
    var Uid: Int = 0
) : Serializable {
    companion object {
        private var instance: UserData? = null

        fun getInstance(): UserData {
            if (instance == null) {
                instance = UserData()
            }
            return instance!!
        }

        fun clearInstance() {
            instance = null
        }
    }
}
