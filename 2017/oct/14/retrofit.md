# Retrofit
We will create a github repo search app! In this excercise we will learn the basics of the Retrofit library, it is written using the Kotlin language.

## Dependencies
First we need to add some dependencies to our project:
```gradle
    compile 'com.android.support:recyclerview-v7:26.1.0'
    compile 'com.squareup.retrofit2:retrofit:2.3.0'
    compile 'com.squareup.retrofit2:converter-gson:2.3.0'
 ```
 
## Layout
We need to create an empty activity in which we will add:
* An EditText
* A Button
* A RecyclerView
* A ProgressBar

Something like this:

<a href="https://ibb.co/k6SuYw"><img src="https://image.ibb.co/fUfEYw/Screen_Shot_2017_10_13_at_10_55_44_PM.png" alt="Screen_Shot_2017_10_13_at_10_55_44_PM" border="0" width="300px"></a>

Do the respective view search in the MainActivity.kt file.

## Adapter
We need to write an adapter for the RecyclerView:

```kotlin
class GitHubAdapter: RecyclerView.Adapter<GitHubAdapter.UserHolder>()  {

    val users: MutableList<GitHubUser> = mutableListOf()

    override fun onBindViewHolder(holder: UserHolder?, position: Int) {
        holder?.bindData(users[position])
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): UserHolder {
        val inflater = LayoutInflater.from(parent?.context)
        val view = inflater.inflate(R.layout.holder_user, parent, false)
        return UserHolder(view)
    }

    fun updateUsers(users: List<GitHubUser>){
        this.users.clear()
        this.users.addAll(users)
        notifyDataSetChanged()
    }


    inner class UserHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        val nameTV: TextView by lazy { itemView.findViewById<TextView>(R.id.name) }
        val scoreTV: TextView by lazy { itemView.findViewById<TextView>(R.id.score) }

        fun bindData(user: GitHubUser){
            nameTV.text = user.login
            scoreTV.text = user.score.toString()
        }

    }
}
```
```holder_user``` is just two TextViews arranged vertically.

Now we have our UI ready to work.

## Models
We need to create kotlin models that GSON can translate from JSON and to GSON. We will use kotlin ```data``` class and the GSON annotation ```@SerializedName()```. This annotation will treat the field with a different key in the JSON object.
For example:
```json
{
"total_count": 1,
    "incomplete_results": false,
    "items": [
        {
          ...
        }
    ]
}
```

Would be:


```kotlin
data class GitHubSearchResult(
        @SerializedName("total_count") val totalCount: Int,
        @SerializedName("incomplete_results") val incompleteResults: Boolean,
        @SerializedName("items") val items: List<GitHubUser>

)
```

Create GitHubUser.kt from this JSON:
```json
{
    "total_count": 1,
    "incomplete_results": false,
    "items": [
        {
            "login": "ivanebernal",
            "id": 16783519,
            "avatar_url": "https://avatars1.githubusercontent.com/u/16783519?v=4",
            "gravatar_id": "",
            "url": "https://api.github.com/users/ivanebernal",
            "html_url": "https://github.com/ivanebernal",
            "followers_url": "https://api.github.com/users/ivanebernal/followers",
            "following_url": "https://api.github.com/users/ivanebernal/following{/other_user}",
            "gists_url": "https://api.github.com/users/ivanebernal/gists{/gist_id}",
            "starred_url": "https://api.github.com/users/ivanebernal/starred{/owner}{/repo}",
            "subscriptions_url": "https://api.github.com/users/ivanebernal/subscriptions",
            "organizations_url": "https://api.github.com/users/ivanebernal/orgs",
            "repos_url": "https://api.github.com/users/ivanebernal/repos",
            "events_url": "https://api.github.com/users/ivanebernal/events{/privacy}",
            "received_events_url": "https://api.github.com/users/ivanebernal/received_events",
            "type": "User",
            "site_admin": false,
            "score": 54.201836
        }
    ]
}
```

## Using Retrofit
We will create an interface where our calls will be placed in form of methods:
```kotlin
interface GithubClient {
    @GET("/search/users")
    fun reposForUser(@Query("q") user: String): Call<GitHubSearchResult>
}
```

In MainActivity.kt, on the onCreate() method, we will create and use a retrofit instance. We will create our retrofit first:

```kotlin
val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://api.github.com")
                .build()
val gitHubClient = retrofit.create(GithubClient::class.java)
```
Finally, we will call our method and define a callback object in which our app will decide what to do in the call's response events. We will call the .enqueue() method:
```kotlin
gitHubClient.reposForUser(searchField.text.toString()).enqueue(
        object : Callback<GitHubSearchResult> {
            override fun onFailure(call: Call<GitHubSearchResult>?, t: Throwable?) {
                t?.printStackTrace()
                Toast.makeText(this@MainActivity, t?.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<GitHubSearchResult>?, response: Response<GitHubSearchResult>?) {
                searchProgress.visibility = View.GONE
                gitHubAdapter.updateUsers(response?.body()?.items?: listOf())
            }

        }
)
```
## Final product
Our search app will look like this:

<a href="https://ibb.co/md8Mfb"><img src="https://preview.ibb.co/jUhgfb/Screen_Shot_2017_10_13_at_11_46_27_PM.png" alt="Screen_Shot_2017_10_13_at_11_46_27_PM" border="0"></a>

if you want to refer to the complete project, please visit <a href="https://github.com/ivanebernal/GitHubDemoKotlin">this repo</a>.
