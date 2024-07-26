package ups.vista;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.BorderFactory;

public class Celda extends JPanel {
    private boolean blocked = false;
    private boolean isStart = false;
    private boolean isEnd = false;
    private Color color = Color.GRAY;
    private Color startColor = Color.GREEN;
    private Color endColor = Color.RED;
    private Color visitedColor = Color.BLUE;
    private Color discardedColor = Color.ORANGE;
    private int x, y;
    private Vista vista;

    public Celda(int x, int y, Vista vista) {
        this.x = x;
        this.y = y;
        this.vista = vista;
        setBackground(color);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (vista.isSettingInicio()) {
                    vista.setInicio(x, y);
                } else if (vista.isSettingFin()) {
                    vista.setFin(x, y);
                } else {
                    if (blocked) {
                        unblock();
                    } else {
                        block();
                    }
                }
            }
        });
    }

    public void setStartColor() {
        isStart = true;
        setBackground(startColor);
        repaint();
    }

    public void setEndColor() {
        isEnd = true;
        setBackground(endColor);
        repaint();
    }

    public void setVisitedColor() {
        setBackground(visitedColor);
        repaint();
    }

    public void setDiscardedColor() {
        setBackground(discardedColor);
        repaint();
    }

    public void resetColor() {
        if (isStart) {
            setBackground(startColor);
        } else if (isEnd) {
            setBackground(endColor);
        } else {
            setBackground(color);
        }
        repaint();
    }

    public void block() {
        blocked = true;
        setBackground(Color.BLACK); 
        repaint();
    }

    public void unblock() {
        blocked = false;
        resetColor();
    }

    public boolean isBlocked() {
        return blocked;
    }

    public boolean isStart() {
        return isStart;
    }

    public void setStart(boolean isStart) {
        this.isStart = isStart;
        if (isStart) {
            setStartColor();
        } else {
            resetColor();
        }
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean isEnd) {
        this.isEnd = isEnd;
        if (isEnd) {
            setEndColor();
        } else {
            resetColor();
        }
    }
}
