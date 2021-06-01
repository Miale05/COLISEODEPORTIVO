package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Schedule")
public class Schedule implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int scheduleId;
	
	@Column(name="scheduleStart", length = 30, nullable=false)
	private String scheduleStart;
	
	@Column(name="scheduleEnd", length = 30, nullable=false)
	private String scheduleEnd;

	public Schedule() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Schedule(int scheduleId, String scheduleStart, String scheduleEnd) {
		super();
		this.scheduleId = scheduleId;
		this.scheduleStart = scheduleStart;
		this.scheduleEnd = scheduleEnd;
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getScheduleStart() {
		return scheduleStart;
	}

	public void setScheduleStart(String scheduleStart) {
		this.scheduleStart = scheduleStart;
	}

	public String getScheduleEnd() {
		return scheduleEnd;
	}

	public void setScheduleEnd(String scheduleEnd) {
		this.scheduleEnd = scheduleEnd;
	}

	
}
