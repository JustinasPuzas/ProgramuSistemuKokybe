public class MainActivity extends AppCompatActivity {
        TimePickerDialog mTimePicker;
        TextView laiko_skirtumas;
        TextView kiekRaidziu;
        TextView display;
        View contextTextView;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                display = (TextView) findViewById(R.id.display);
                laiko_skirtumas = (TextView)findViewById(R.id.laikoSkirtumas);
                kiekRaidziu = (TextView)findViewById(R.id.raidziuKiekis);
                System.out.println(laiko_skirtumas.getText());
                registerForContextMenu(laiko_skirtumas);
                registerForContextMenu(kiekRaidziu);
                long hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
                long minute = Calendar.getInstance().get(Calendar.MINUTE);

                 mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int i, int i1) {
                                laiko_skirtumas.setText("Skirtumas tarp dabar ir nurodyto laiko yra "+ count(i, i1) + " minutės");

                        }
                }, (int)hour, (int) minute, false);
        }
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.main_menu, menu);
                return true;
        }
        @SuppressLint("ResourceType")
        @Override
        public boolean onContextItemSelected(@NonNull MenuItem item) {
                TextView chosenText = (TextView) findViewById(contextTextView.getId());
                String key = (String) chosenText.getText();


                switch(item.getItemId()) {
                        case R.id.cCount:
                                kiekRaidziu.setText("Tekste yra " + key.length() + " simboliu");
                                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                builder.setTitle("Simboliu skaicius");
                                builder.setMessage("Tekste yra " + key.length() + " simboliu");
                                AlertDialog dialog = builder.create();
                                dialog.show();
                                return true;
                        case R.id.cDisplay:
                                Thread a = new Thread(new thread1(key));
                                a.start();
                                return true;


                }
                return super.onContextItemSelected(item);
        }
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                super.onCreateContextMenu(menu, v, menuInfo);
                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.context_menu, menu);
                contextTextView = v;

        }
        private int count(int hours, int minutes){ //this method counts difference between times in minutes
                int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
                int currentMinute = Calendar.getInstance().get(Calendar.MINUTE);
                int currentTimeInMinutes = currentMinute + currentHour * 60;
                int chosenTimeInMinutes = minutes+ hours * 60;
                return Math.abs(chosenTimeInMinutes-currentTimeInMinutes);
        }
        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                        case R.id.valandu_skirtumas:
                                mTimePicker.show();
                                break;
                        case R.id.action_settings:
                                finish();


                }
                return super.onOptionsItemSelected(item);
        }
        class thread1 implements Runnable{// this class creates thread
                String tekstas;
                public thread1(String text){
                        this.tekstas = text;
                }
                public void run(){
                        for(int i=0;i<tekstas.length();i++){
                                runOnUiThread(this::run);

                                display.setText(tekstas.charAt(i)+"");
                                try {
                                        Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                        e.printStackTrace();
                                }
                        }
                        display.setText("X");
                }
        }
}
