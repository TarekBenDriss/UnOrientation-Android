package bendriss.tarek.unorientation.data.source.remote.service;



import bendriss.tarek.unorientation.data.source.local.entity.UserProfile;
import bendriss.tarek.unorientation.data.source.remote.params.LoginParams;
import bendriss.tarek.unorientation.data.source.remote.params.QuizParam;
import bendriss.tarek.unorientation.data.source.remote.response.LoginResponse;
import bendriss.tarek.unorientation.data.source.remote.response.QuizResponse;
import bendriss.tarek.unorientation.data.source.remote.response.SignupResponse;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * this class represents the service endpoint
 */
public interface ServiceEndpoint {

    String BASE_URL = "http://5.135.52.75:8090/";
    //String BASE_URL = NetworkConstants.BASE_URL;

    String LOGIN = "login";
    String SIGNUP = "signup/";
    String CODE_AGENCE = "api/mobile/users/key/{userKey}";
    String GET_EXPENSE_DETAILS = "/api/mobile/expenseReports/{expenseReportId}";
    String MANAGER = "api/mobile/managers/{id}";
    String MY_NEWS = "api/mobile/news";
    String ADD_EXPENSES = "api/mobile/expenseReports/create";
    String ADD_DOCUMENT = "api/mobile/documents/uploadDocuments";
    String ADD_LEAVE = "api/mobile/leaves/create";
    String MY_SALARIES = "api/mobile/salaryAdvances/search";
    String LEAVES = "api/mobile/leaves/search";
    String MISSIONS = "api/mobile/missions";
    String MISSION_DETAILS = "api/mobile/missions/{id}";
    String GET_USER_PROFILE = "api/mobile/users/current";
    String GET_NEWS_DETAILS = "api/mobile/news/{id}";
    String GET_PRIORITIES = "api/mobile/priorities";
    String GET_CURRENCIES = "api/mobile/currencies";
    String GET_LEAVES_TYPE = "api/mobile/leaveTypes";
    String GET_REPORT_TYPES = "api/mobile/expenseReportTypes";
    String GET_COMPANIES = "api/mobile/companies/search";
    String GET_EXPENSES = "api/mobile/expenseReports/search";
    String ADD_SALARY_ADVANCE = "api/mobile/salaryAdvances/create";

    /*Params*/

    String USERNAME = "username";
    String PASSWORD = "password";
    String AUTO = "auto";
    String USER_KEY = "userKey";
    String EXPENSE_REPORT_ID = "expenseReportId";
    String ID = "id";
    String NAME = "name";
    String GET_QUIZ = "quiz/{name}";
    String GET_QUIZ_V2 = "quiz/get";


    @POST(LOGIN)
    Single<LoginResponse> login(@Query(USERNAME) String username, @Query(PASSWORD) String password);

    @POST(SIGNUP)
    Single<SignupResponse> signup(@Body UserProfile userProfile);

    @GET(GET_QUIZ)
    Single<QuizResponse> getQuiz(@Path(NAME) String name);

    @POST(GET_QUIZ_V2)
    Single<QuizResponse> getQuizV2(@Body QuizParam quizParam);


    /*
    @GET(CODE_AGENCE)
    Single<VerifAgenceResponse> verifCodeAgence(@Path(USER_KEY) String codeAgence);

    @GET(GET_EXPENSE_DETAILS)
    Single<ExpenseReportResponse> getDetailsExpense(@Path(EXPENSE_REPORT_ID) String expenseReportId);

    @POST(MY_NEWS)
    Single<List<NewsResponse>> getNews();

    @POST(MY_SALARIES)
    Single<List<SalaryResponse>> getSalaries(@Body SalaryParam param);

    @POST(LEAVES)
    Single<List<LeaveResponse>> getLeaves(@Body SalaryParam param);

    @GET(MISSIONS)
    Single<List<MissionsResponse>> getMissions();

    @GET(MISSION_DETAILS)
    Single<MissionDetailsResponse> getDetailMission(@Path(ID) String id);

    @GET(GET_USER_PROFILE)
    Single<ProfileResponse> getMyProfile();


    @GET(GET_NEWS_DETAILS)
    Single<NewsDetailsResponse> getNewsDetails(@Path(ID) String id);

    @GET(MANAGER)
    Single<ManagerResponse> getManager(@Path(ID) String id);

    @POST(ADD_EXPENSES)
    Single<AddExpenseResponse> addExpense(@Body ExpensesParam param);


    @Multipart
    @POST(ADD_DOCUMENT)
    Single<String> addDocument(@Part("document") File file, @Query("subjectId") String subjectId, @Query("subjectType") String subjectType, @Query("documentType") String documentType);

    @Multipart
    @POST(ADD_DOCUMENT)
    Single<UploadResponse> uploadAttachment(@Part MultipartBody.Part file, @Part("subjectId") RequestBody subjectId, @Part("subjectType") RequestBody subjectType, @Part("documentType") RequestBody documentType);
    //Single<UploadResponse> uploadAttachment(@Part MultipartBody.Part file, @Query("subjectId") String subjectId, @Query("subjectType") String subjectType, @Query("documentType") String documentType);



    @POST(ADD_LEAVE)
    Single<String> addLeave(@Body LeaveParam param);

    @POST(ADD_SALARY_ADVANCE)
    Single<String> addSalaryAdvance(@Body SalaryAdvanceParam param);

    @GET(GET_PRIORITIES)
    Single<List<LabelResponse>> getPriorities();

    @GET(GET_CURRENCIES)
    Single<List<LabelResponse>> getCurrencies();

    @GET(GET_LEAVES_TYPE)
    Single<List<LabelResponse>> getLeavesType();

    @GET(GET_REPORT_TYPES)
    Single<List<LabelResponse>> getReportType();

    @POST(GET_COMPANIES)
    Single<List<CompanyResponse>> getCompanies(@Body CompanyParam param);

    @POST(GET_EXPENSES)
    Single<List<ExpenseReportResponse>> getExpenses(@Body SalaryParam param);
    */
}
