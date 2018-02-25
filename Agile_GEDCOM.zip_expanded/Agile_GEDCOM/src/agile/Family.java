package agile;

//import java.util.*;

public class Family {

	private String id;
	private Individual husband;
	private Individual wife;
	private String husbandId;
	private String wifeId;
	private String weddingDate;
	private String DivorceDate;
	private String childId = "";// Id's of children are saved here

	public void setHusband(Individual husband) {
		this.husband = husband;
	}

	public void setWife(Individual wife) {
		this.wife = wife;
	}

	public Individual getHusband() {
		return this.husband;
	}

	public Individual getWife() {
		return this.wife;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHusbandId() {
		return husbandId;
	}

	public void setHusbandId(String husbandId) {
		this.husbandId = husbandId;
	}

	public String getWifeId() {
		return wifeId;
	}

	public void setWifeId(String wifeId) {
		this.wifeId = wifeId;
	}

	public String getChildId() {
		return childId;
	}

	public void setChildId(String childId) {

		this.childId = this.childId + " " + childId;
		// System.out.println(this.childId);

	}

	public String getWeddingDate() {
		return weddingDate;
	}

	public void setWeddingDate(String wdate) {
		this.weddingDate = wdate;
	}

	public String getDivorceDate() {
		return DivorceDate;
	}

	public void setDivorceDate(String divorceDate) {
		this.DivorceDate = divorceDate;
	}

}
