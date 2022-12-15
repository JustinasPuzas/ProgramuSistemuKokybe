public class Board  extends JPanel implements Runnable
{
    boolean ingame = true;
    private Dimension d;
    int BOARD_WIDTH=600;
    int BOARD_HEIGHT=400;
    private Thread animator;
    Player p;
    Missile m;
    EnemyMissile em;
    ArrayList<Alien> a = new ArrayList<>(15);
    public Board()
    {
        addKeyListener(new TAdapter());
        setFocusable(true);
        d = new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
        p = new Player(BOARD_WIDTH/2, BOARD_HEIGHT, 4);
        int ax = 10;
        int ay = 10;
        for(int i = 0; i<15; i++){
            a.add(i, new Alien(ax, ay, 2));
            ax += 40;
            if(i==4){
                ax = 10;
                ay += 40;
            }
            if(i==9){
                ax=10;
                ay+= 40;
            }
        }
        if (animator == null || !ingame) {
            animator = new Thread(this);
            animator.start();
        }
        setDoubleBuffered(true);

        long delay = 3000;
        ActionListener enemyMissile = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                int randomEnemyIndex = (int) (Math.random() * ((a.size())));
                    if(a.size()!=0){
                    em = new EnemyMissile(a.get(randomEnemyIndex).x + 10, a.get(randomEnemyIndex).y + 30, 2);
                    }
            }
        };
        Timer timer = new Timer((int) delay, enemyMissile);
        timer.start();
    }

    public void paint(Graphics g) // this method
    {
        super.paint(g);

        g.setColor(Color.white);
        g.fillRect(0, 0, d.width, d.height);

        g.setColor(Color.ORANGE);
        int [] x = {p.x-20, p.x+20, p.x};
        int [] y = {p.y, p.y, p.y-30};
        g.fillPolygon(x, y, 3);
        if(p.moveRight==true&&p.x<BOARD_WIDTH-33){
            if(p.x+p.speed>BOARD_WIDTH-33){
                p.x=BOARD_WIDTH-33;
            }
            else {
                p.x += p.speed;
            }
        }
        if(p.moveLeft == true&&p.x>20){
            if(p.x-p.speed <0){
                p.x=20;
            }
            else {
                p.x -= p.speed;
            }
        }
        g.setColor(Color.black);
        moveAliens();
        for(int i = 0; i<a.size(); i++){
            if(a.get(i).isVisible) {
                g.fillRect(a.get(i).x, a.get(i).y, 30, 30);
            }
        }
        g.setColor(Color.red);
        if(m!=null) {
            g.fillOval(m.x, m.y, 10, 10);
            moveMissile();
        }
        if(em!=null){
            g.fillOval(em.x, em.y, 10, 10);
            moveEnemyMissile();
        }
        if(!ingame){
            setVisible(false);
            g.dispose();
        };
    }
