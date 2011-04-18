package extend.fractal;

/*�˲��֣���1000*1000�������ϰ�DLA��ʽ����10����ڵ㡣
 **/
import java.util.*;
import java.math.*;

public class Fractal {
	int i = 0, j = 0, t = 0, n = 0;
	int loc_x, loc_y; // ��������
	int upordown;
	int radius;
	int distance;
	int forward;
	int l3, driver, graphmode;
	long l1, l2; // l1:�������
	int grids[][] = new int[1000][1000]; // ����1000000�������
	Random random = new Random();

	JiNetworkTopology genfractalTopology(int Radius) {
		radius = Radius;
		for (i = 0; i < 1000; i++) {
			for (j = 0; j < 1000; j++)
				grids[i][j] = 0;
		}
		grids[500][500] = 1; // ���ĵ���1
		while (t < 100000) { // �ͷ�һ������
			loc_x = random.nextInt(1000); // �ͷŵ�ĺ�����
			loc_y = (int) Math.sqrt(radius * radius - (loc_x - 500)* (loc_y - 500)); // ���ͷŵ��������
			upordown = random.nextInt(2); // ���������ĵ���ϲ���²�
			if (upordown == 0) {
				loc_y = 500 + loc_y;
			} else {
				loc_y = 500 - loc_y;
			}
			if (grids[loc_x][loc_y + 1] == 1 | grids[loc_x][loc_y - 1] == 1
					| grids[loc_x + 1][loc_y] == 1
					| grids[loc_x - 1][loc_y] == 1)
				continue; // �����ʼ������������Χ���������壬�������ͷ����ӡ����뾶Բ�ϵĵ㲻�ܱ�����)
			while (true) // ÿ�ƶ�һ�����ж��Ƿ�Խ�磬�Ƿ���Χ�������塣
			{
				forward = random.nextInt(4); // ��ǰ�˶�һ��
				if (forward == 0)
					loc_x = loc_x + 1;
				if (forward == 1)
					loc_x = loc_x - 1;
				if (forward == 2)
					loc_y = loc_y + 1;
				if (forward == 3)
					loc_y = loc_y - 1;
				distance = (int) Math.sqrt((loc_x - 500) * (loc_x - 500)
						+ (loc_y - 500) * (loc_y - 500));
				if (distance > radius)  //�ж��Ƿ�Խ��
					break;             //Խ��-�����ͷ�
				if (grids[loc_x][loc_y + 1] == 1|grids[loc_x][loc_y - 1] == 1
			     	 |grids[loc_x + 1][loc_y] == 1|grids[loc_x - 1][loc_y] == 1)
				      {  t++;                  //�������Ӹ���
				         grids[loc_x][loc_y] = 1; 
				         JiNode[t++].loc_x = loc_x; //������������(���Ӽ�Ϊ����ڵ�)
				         JiNode[t++].loc_y = loc_y;
				         break; } //�����ͷ�����
				else {
					       continue; //��ǰ�ڵ���Χ���û�������壬�����ƶ�	
				}
			}
		}
	}

}
