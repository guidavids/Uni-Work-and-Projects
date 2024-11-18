using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class enemyDamage : MonoBehaviour
{
    public float damage; // How much damage the enemy can do
    public float damageSpeed; // How fast the damage is dealt
    public float knockBackForce; // The knockback the player will suffer upon contacting the enemy
    float nextDamage; // Next time the damage can occurr

    // Start is called before the first frame update
    void Start()
    {
        nextDamage = 0f;
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    void OnTriggerStay2D(Collider2D other)
    {
        if(other.tag == "Player" && nextDamage < Time.time)
        {
            playerHealth pcHealth = other.gameObject.GetComponent<playerHealth>();
            pcHealth.playerTakeDamage(damage);
            nextDamage = Time.time + damageSpeed;

            knockBack(other.transform);
        }
    }

    void knockBack(Transform pushedObject)
    {
        Vector2 knockDirection = new Vector2(0, (pushedObject.position.y - transform.position.y)).normalized;

        knockDirection = knockDirection * knockBackForce;

        Rigidbody2D pushRB = pushedObject.gameObject.GetComponent<Rigidbody2D>();
        pushRB.velocity = Vector2.zero;
        pushRB.AddForce(knockDirection, ForceMode2D.Impulse);
    }
}
