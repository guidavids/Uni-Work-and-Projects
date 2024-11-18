using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class shootBlast : MonoBehaviour
{
    public GameObject shooterProjectile;
    public float shootTime;
    public int chanceShoot;
    public Transform shootFrom;
    float nextShootTime;
    Animator shooterAnim;

    // Start is called before the first frame update
    void Start()
    {
        shooterAnim = GetComponentInChildren<Animator>();
        nextShootTime = 0f;
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    void OnTriggerStay2D(Collider2D other)
    {
        if(other.tag == "Player" && nextShootTime < Time.time)
        {
            nextShootTime = Time.time + shootTime;

            if(Random.Range(0, 10) >= chanceShoot)
            {
                Instantiate(shooterProjectile, shootFrom.position, Quaternion.identity);
                shooterAnim.SetTrigger("shooterShoot");
            }
        }
    }
}
