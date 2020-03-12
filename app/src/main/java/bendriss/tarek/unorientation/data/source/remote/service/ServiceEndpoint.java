package bendriss.tarek.unorientation.data.source.remote.service;



import bendriss.tarek.unorientation.data.source.remote.params.LoginParams;
import bendriss.tarek.unorientation.data.source.remote.response.LoginResponse;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * this class represents the service endpoint
 */
public interface ServiceEndpoint {

    String BASE_URL = "https://unorientation.herokuapp.com/";
    //String BASE_URL = NetworkConstants.BASE_URL;

    String LOGIN = "login";
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
    String AUTO = "auto";
    String USER_KEY = "userKey";
    String EXPENSE_REPORT_ID = "expenseReportId";
    String ID = "id";


    @POST(LOGIN)
    Single<LoginResponse> login(@Body LoginParams loginParams);

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