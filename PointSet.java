package oo.hide;

import java.util.Arrays;
import java.util.Objects;

public class PointSet
{

    Point[] points;
    int point_number = 0;
    public PointSet(int capacity)
    {
        points = new Point[capacity];
    }

    public PointSet()
    {
        this(10);
    }

    public void add(Point point)
    {
        if(!(point_number < points.length))
        {
            Point[] temp = new Point[points.length * 2];
            for(int i = 0; i < point_number; i++)
            {
                temp[i] = points[i];
            }
            points = temp;
        }

        if(!this.contains(point))
        {
            points[point_number] = point;
            point_number++;
        }
    }

    public int size()
    {
        return point_number;
    }

    public boolean contains(Point point)
    {
        boolean result = false;

        for(int i = 0; i < point_number; i++)
        {
            if((points[i].getX() == point.getX())&&(points[i].getY() == point.getY()))
            {
                result = true;
            }
        }
        return result;
    }

    public PointSet subtract(PointSet other)
    {
        PointSet result = new PointSet(points.length);

        for(int i = 0; i < point_number; i++)
        {
            if(!other.contains(points[i]))
            {
                result.add(points[i]);
            }
        }

        return result;
    }

    public PointSet intersect(PointSet other)
    {
        PointSet result = new PointSet(points.length);

        for(int i = 0; i < point_number; i++)
        {
            if(other.contains(points[i]))
            {
                result.add(points[i]);
            }
        }

        return result;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < point_number; i++)
        {
            sb.append('(');
            sb.append(points[i].getX());
            sb.append(',');
            sb.append(' ');
            sb.append(points[i].getY());
            sb.append(')');
            if(i < point_number - 1)
            {
                sb.append(',');
                sb.append(' ');
            }
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof PointSet))
        {
            return false;
        }

        PointSet other = (PointSet) obj;
       boolean result = true;

       for(int i = 0; i < point_number; i++)
       {
           if(!other.contains(points[i]))
           {
               result = result && false;
           }
       }
       return result;
    }

    @Override
    public int hashCode()
    {
        int result = Objects.hash(point_number);
        result = 31 * result + Arrays.hashCode(points);
        return result;
    }
}
