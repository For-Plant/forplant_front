package com.example.forplant_front

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.forplant_front.connection.RetrofitClient2
import com.example.forplant_front.databinding.ActivityAgree2Binding
import com.example.forplant_front.databinding.ActivityAgree3Binding
import com.example.forplant_front.databinding.ActivityProfilenewBinding
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File
import java.util.regex.Pattern


class ProfilenewActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfilenewBinding
    private val PICK_IMAGE_REQUEST = 1 // 이미지를 선택하는 요청 코드
    private var isPasswordValid = false
    private var isPasswordMatching = false
    var isPasswordVisible = false
    private var selectedImageUri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilenewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val eyeImageView = binding.imageView11
        val eyeCheckImageView = binding.imageView12

        val textPassword = binding.Idedittext3
        val textPasswordCheck = binding.Idedittext4

        val textId = binding.Idedittext2

        binding.passworwrong.visibility = View.GONE

        binding.imageView2.setOnClickListener {
            finish()
        }
        binding.button.setOnClickListener {
            val phonenum = binding.Idedittext5.text.toString()
            val username = binding.nameIdedittext.text.toString()
            val nickname = binding.Idedittext.text.toString()
            val member_id = binding.Idedittext2.text.toString()
            val password = binding.Idedittext3.text.toString()

            val usernamePart = username.toRequestBody("text/plain".toMediaTypeOrNull())
            val nickNamePart = nickname.toRequestBody("text/plain".toMediaTypeOrNull())
            val phonenumPart = phonenum.toRequestBody("text/plain".toMediaTypeOrNull())
            val passwordPart = password.toRequestBody("text/plain".toMediaTypeOrNull())
            val member_idPart = member_id.toRequestBody("text/plain".toMediaTypeOrNull())
            val marketingAgreeValue = 1 // 또는 0
            val marketingAgreePart = marketingAgreeValue.toString().toRequestBody("text/plain".toMediaTypeOrNull())

            // selectedImageUri를 로컬 변수에 저장
            val localSelectedImageUri = selectedImageUri

            if (localSelectedImageUri == null) {
                // 이미지 URI가 null이면 서버에는 이미지를 업로드하지 않고, 닉네임과 비밀번호만 전송
                val call = RetrofitObject.getRetrofitService.signup(usernamePart, nickNamePart, member_idPart, passwordPart,phonenumPart,marketingAgreePart,null)
                call.enqueue(object : Callback<RetrofitClient2.Responsesignup> {
                    override fun onResponse(call: Call<RetrofitClient2.Responsesignup>, response: Response<RetrofitClient2.Responsesignup>) {
                        Log.d("Retrofit430", response.toString())
                        if (response.isSuccessful) {
                            Log.d("Retrofit410", response.toString())
                            val responseBody = response.body()
                            Log.d("Retrofit40", responseBody.toString())
                            if (responseBody != null && responseBody.isSuccess) {
                                val intent = Intent(this@ProfilenewActivity, SplashwelcomeActivity::class.java)
                                startActivity(intent)
                            } else {
                                Toast.makeText(
                                    this@ProfilenewActivity,
                                    responseBody?.message ?: "Unknown error",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }

                    override fun onFailure(call: Call<RetrofitClient2.Responsesignup>, t: Throwable) {
                        val errorMessage = "Call Failed: ${t.message}"
                        Log.d("Retrofit0", errorMessage)
                    }
                })
            } else {
                val compressedImageData = compressAndEncodeImage(localSelectedImageUri)
                // 이미지 URI가 null이 아니면 이미지를 서버에 업로드하고 닉네임과 비밀번호도 함께 전송
                val file = File(cacheDir, "image.jpg")
                file.writeBytes(Base64.decode(compressedImageData, Base64.DEFAULT))

                // 이미지 파일을 서버로 전송
                val requestFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
                val body = MultipartBody.Part.createFormData("image", file.name, requestFile)

                Log.d("Retrofit431", body.toString())
                val call = RetrofitObject.getRetrofitService.signup(
                    usernamePart,
                    nickNamePart,
                    member_idPart,
                    passwordPart,
                    phonenumPart,
                    marketingAgreePart,
                    body
                )
                call.enqueue(object : Callback<RetrofitClient2.Responsesignup> {
                    override fun onResponse(
                        call: Call<RetrofitClient2.Responsesignup>,
                        response: Response<RetrofitClient2.Responsesignup>
                    ) {
                        Log.d("Retrofit43", response.toString())
                        if (response.isSuccessful) {
                            Log.d("Retrofit41", response.toString())
                            val responseBody = response.body()
                            Log.d("Retrofit4", responseBody.toString())
                            if (responseBody != null && responseBody.isSuccess) {
                                val intent = Intent(this@ProfilenewActivity, SplashwelcomeActivity::class.java)
                                startActivity(intent)
                            } else {
                                Toast.makeText(
                                    this@ProfilenewActivity,
                                    responseBody?.message ?: "Unknown error",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }

                    override fun onFailure(
                        call: Call<RetrofitClient2.Responsesignup>,
                        t: Throwable
                    ) {
                        val errorMessage = "Call Failed: ${t.message}"
                        Log.d("Retrofit", errorMessage)
                    }
                })
            }
        }

        binding.imageView18.setOnClickListener {
            openGallery()
        }

        eyeImageView.setOnClickListener {
            togglePasswordVisibility(textPassword, eyeImageView)
        }
        eyeCheckImageView.setOnClickListener {
            togglePasswordVisibility(textPasswordCheck, eyeCheckImageView)
        }

        textId.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val call = RetrofitObject.getRetrofitService.getid(textId.text.toString())
                call.enqueue(object : Callback<RetrofitClient2.Responsegetid> {
                    override fun onResponse(call: Call<RetrofitClient2.Responsegetid>, response: Response<RetrofitClient2.Responsegetid>) {
                        Log.d("Retrofit21", response.toString())
                        if (response.isSuccessful) {
                            val response = response.body()
                            Log.d("Retrofit2", response.toString())
                            if (response != null) {
                                if (response.isSuccess) {
                                    binding.textView38.visibility = View.GONE
                                    binding.textView382.visibility = View.VISIBLE
                                    binding.textView383.visibility = View.GONE
                                }
                                else{
                                    binding.textView38.visibility = View.GONE
                                    binding.textView382.visibility = View.GONE
                                    binding.textView383.visibility = View.VISIBLE
                                }
                            }
                        }
                    }
                    override fun onFailure(call: Call<RetrofitClient2.Responsegetid>, t: Throwable) {
                        val errorMessage = "Call Failed: ${t.message}"
                        Log.d("Retrofit", errorMessage)
                    }
                })
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        textPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // 입력된 비밀번호가 조건을 만족하는지 확인
                isPasswordValid = isPasswordValid(textPassword.text.toString())

                // 비밀번호가 조건을 만족하지 않으면 textchecking의 visibility를 View.VISIBLE로 설정
                if (!isPasswordValid) {
                    binding.textView40.visibility = View.GONE
                    binding.textView404.visibility = View.VISIBLE
                } else {
                    // 비밀번호가 조건을 만족하면 textchecking의 visibility를 View.GONE으로 설정
                    binding.textView40.visibility = View.GONE
                    binding.textView404.visibility = View.GONE
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        // textPasswordCheck의 텍스트 변경 감지
        textPasswordCheck.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // textPassword와 textPasswordCheck의 값이 같을 때 배경을 업데이트
                isPasswordMatching = textPassword.text.toString() == textPasswordCheck.text.toString()

                if (isPasswordMatching) {
                    binding.passworwrong.visibility = View.GONE
                } else {
                    binding.passworwrong.visibility = View.VISIBLE
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })


    }
    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.data!!

            // 이제 selectedImageUri를 사용하여 이미지를 처리하고, ImageView에 설정할 수 있습니다.
            // 예시로 Glide 사용하는 방법:
            Glide.with(this)
                .load(selectedImageUri)
                .into(binding.imageView18)

            binding.soldoutlayout.visibility= View.GONE
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        // 비밀번호가 8자리 이상이며 영문, 숫자, 특수문자를 포함하는지 확인하는 로직
        val pattern = Pattern.compile("(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@$!%*?&]{8,}")
        val matcher = pattern.matcher(password)
        return matcher.matches()
    }
    private fun togglePasswordVisibility(editText: EditText, imageView: ImageView) {
        // 비밀번호 보이기/감추기 기능 구현
        isPasswordVisible = !isPasswordVisible

        if (isPasswordVisible) {
            // 비밀번호를 보이도록 변경
            editText.transformationMethod = HideReturnsTransformationMethod.getInstance()
            imageView.setImageResource(R.drawable.eye_on)
        } else {
            // 비밀번호를 감추도록 변경
            editText.transformationMethod = PasswordTransformationMethod.getInstance()
            imageView.setImageResource(R.drawable.eye_off)
        }

        // 커서를 마지막으로 이동하여 비밀번호 글자가 보이도록 함
        editText.setSelection(editText.text.length)
    }

    private fun compressAndEncodeImage(imageUri: Uri): String? {
        val inputStream = contentResolver.openInputStream(imageUri)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        inputStream?.close()

        val maxHeight = 1024.0f
        val maxWidth = 1024.0f
        val scale = Math.min(maxWidth / bitmap.width, maxHeight / bitmap.height)

        val matrix = Matrix()
        matrix.postScale(scale, scale)

        val scaledBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)

        val byteArrayOutputStream = ByteArrayOutputStream()
        scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream) // 조정 가능한 압축률 및 포맷 설정
        val byteArray = byteArrayOutputStream.toByteArray()

        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }
}
