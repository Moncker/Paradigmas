public abstract class TestBed {
    protected WeatherAppFacade weatherStation;

    @Before
    public void setUp() throws Exception {
        Weather weather = new Weather();
        Localizacion loc= new Localizacion();
        weatherStation = new WeatherS(multiplier, divider);
    }

    @After
    public void tearDown() throws Exception {
        weatherStation = null;
    }
}