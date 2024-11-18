using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class enemyMovementController : MonoBehaviour
{
    public float enemySpeed; // Variable to set enemy speed
    Animator enemyAnim; // Setting up a variable to get the Animator
    Rigidbody2D enemyRB;

    // Variables for direction enemy is facing
    public GameObject enemySprite; // Variable to set up the game object this is connected to
    bool canFlip = true; // Variable to check if enemy can flip
    bool facingRight = true;
    float flipTime = 5f;
    float nextFlipChance = 0f;

    //Attacking
    public float chargeTime;
    float startChargeTime;
    bool charging;

    // Start is called before the first frame update
    void Start()
    {
        enemyAnim = GetComponentInChildren<Animator>();
        enemyRB = GetComponent<Rigidbody2D>();
    }

    // Update is called once per frame
    void Update()
    {
        if(Time.time > nextFlipChance)
        {
            if(Random.Range(0, 10) >= 5)
            {
                flipFacing();
            }
            nextFlipChance = Time.time + flipTime;
        }
    }


    void OnTriggerEnter2D(Collider2D other)
    {
        if(other.tag == "Player")
        {
            if(facingRight && other.transform.position.x < transform.position.x)
            {
                flipFacing();
            }
            else if(!facingRight && other.transform.position.x > transform.position.x)
            {
                flipFacing();
            }

            canFlip = false; // Once enemy is facing the correct way, stop flipping
            charging = true; // 
            startChargeTime = Time.time + chargeTime;
        }
    }

    void OnTriggerStay2D(Collider2D other)
    {
        if(other.tag == "Player")
        {
            if(startChargeTime < Time.time)
            {
                if(!facingRight)
                {
                    enemyRB.AddForce(new Vector2(-1, 0) * enemySpeed);
                }
                else if(facingRight)
                {
                    enemyRB.AddForce(new Vector2(1, 0) * enemySpeed);
                }
                
                enemyAnim.SetBool("isCharging", charging);
            }
        }
    }

    void OnTriggerExit2D(Collider2D other)
    {
        if(other.tag == "Player")
        {
            canFlip = true;
            charging = false;
            enemyRB.velocity = new Vector2(0f, 0f);
            enemyAnim.SetBool("isCharging", charging);
        }
    }

    public void stopMoving()
    {
        charging = false;
        canFlip = false;
        enemyRB.velocity = Vector2.zero;
    }

    void flipFacing()
    {
        if(!canFlip)
        {
            return;
        }
        // Variables to dictate where the creature is facing
        float facingX = enemySprite.transform.localScale.x;
        facingX = facingX * -1f;
        enemySprite.transform.localScale = new Vector3(facingX, enemySprite.transform.localScale.y, enemySprite.transform.localScale.z);
        facingRight = !facingRight;
    }
}
