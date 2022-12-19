public class MainActivity extends AppCompatActivity {
        TimePickerDialog mTimePicker;
        TextView timeDifference;
        TextView letterCount;
        TextView display;
        View contextTextView;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                display = (TextView) findViewById(R.id.display);
                timeDifference = (TextView)findViewById(R.id.time_difference);
                letterCount = (TextView)findViewById(R.id.letter_count);
                System.out.println(timeDifference.getText());
                registerForContextMenu(timeDifference);
                registerForContextMenu(letterCount);
                int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
                int minute = Calendar.getInstance().get(Calendar.MINUTE);

                 mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int i, int i1) {
                                timeDifference.setText("Skirtumas tarp dabar ir nurodyto laiko yra "+ countTimeDifferenceInMinutes(i, i1) + " minutės");

                        }
                }, hour, minute, false);
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
                                letterCount.setText("Tekste yra " + key.length() + " simboliu");
                                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                builder.setTitle("Simboliu skaičius");
                                builder.setMessage("Tekste yra " + key.length() + " simbolių");
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

        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                        case R.id.set_hours:
                                mTimePicker.show();
                                break;
                        case R.id.action_settings:
                                finish();


                }
                return super.onOptionsItemSelected(item);
        }
        private int getCurrentTimeInMinutes(){
                int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
                int currentMinute = Calendar.getInstance().get(Calendar.MINUTE);
                return currentMinute + currentHour * 60;

        }
        private int countTimeDifferenceInMinutes(int hours, int minutes){
                int chosenTimeInMinutes = minutes+ hours * 60;
                return Math.abs(chosenTimeInMinutes-getCurrentTimeInMinutes());
        }

        class thread1 implements Runnable{
                String textForDisplay;
                public thread1(String text){
                        this.textForDisplay = text;
                }
                public void run(){
                        for(int i=0;i<textForDisplay.length();i++){
                                runOnUiThread(this::run);

                                display.setText(textForDisplay.charAt(i)+"");
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
