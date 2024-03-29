package md.webmaster.borgi.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import md.webmaster.borgi.data.UserDao
import md.webmaster.borgi.data.UserEntity
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import md.webmaster.borgi.tools.BitmapConverter.convertStringToBitmap
import md.webmaster.borgi.data.DebtDao

class UserViewModel(val userDao: UserDao, val debtDao: DebtDao): ViewModel() {

    val signatureBitmap: MutableLiveData<Bitmap?> = MutableLiveData()

    fun insert(user: UserEntity) {
        viewModelScope.launch {
            userDao.insert(user)
        }
    }

    fun deleteAll() {
        viewModelScope.launch {
            userDao.deleteAll()
        }
    }

    fun getAll(): List<UserEntity> {
        return userDao.getAll()
    }

    fun loadSignature() = runBlocking {
        val user = userDao.getAll()[0]

        val signature = user.signature
        val bitmap = convertStringToBitmap(signature)
        signatureBitmap.postValue(bitmap)
    }

    fun addSelectedImage(imageBitmap: Bitmap) {
        signatureBitmap.postValue(imageBitmap)
    }

}