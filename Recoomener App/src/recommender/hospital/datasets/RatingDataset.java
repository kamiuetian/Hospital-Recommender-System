package recommender.hospital.datasets;

/*
 * 
 * This class contains the rating of each hospital
 * The fields are user Id, hospital Id, hospital Rating
 * 
 * */
public class RatingDataset {
	private int user_Id;
	private int hospital_Id;
	private float hospital_Rating;
	/*Constructor*/
	RatingDataset(int u_Id, int h_Id, float h_R)
	{
		this.hospital_Id=h_Id;
		this.user_Id=u_Id;
		this.hospital_Rating=h_R;
	}
	/*Get User Id*/
	public int getuser_Id()
	{
		return this.user_Id;
	}
	/*Get hospital Id*/
	public int gethospital_Id()
	{
		return this.hospital_Id;
	}
	/*Get hospital Rating*/
	public float gethospital_Rating()
	{
		return this.hospital_Rating;
	}
	/*Set hospital ratings*/
	public void sethospital_Rating(float rating)
	{
		this.hospital_Rating=rating;
	}
}
