using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class enemyHealth : MonoBehaviour
{
    public float maxEnemyHealth; // Max health an enemy can have
    float currentEnemyHealth; // Current enemy health at any given time
    public GameObject enemyDeathFX; // Particles that will appear when the enemy dies
    public Slider enemyHealthSlider; // Create a variable to add the slider
    Animator enemyAnim; // Preparing to call the Animator object

    enemyMovementController enemyController; // Preparing to call the enemyMovementController class

    public AudioClip deathSound; // Set variable to play an audio clip on enemy death
    public AudioClip hitSound; // Set variable to play an audio clip when an enemy is hit

    public bool canDrop;
    public GameObject healthDrop;
    
    public bool canSpecialDrop;
    public GameObject specialDrop;

    // Start is called before the first frame update
    void Start()
    {
        currentEnemyHealth = maxEnemyHealth; // Set the current enemy health to max in the first frame
        enemyHealthSlider.maxValue = currentEnemyHealth; // Set the enemy healthbar to a max of the current enemy health
        enemyHealthSlider.value = currentEnemyHealth; // Set the slider to the current enemy health
        enemyAnim = GetComponent<Animator>(); // Set the animator object
        enemyController = GetComponentInParent<enemyMovementController>(); // Set the enemyMovementController Class
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    public void addDamage(float damage)
    {
        currentEnemyHealth = currentEnemyHealth - damage; // Update enemy health after damage

        enemyHealthSlider.value = currentEnemyHealth; // Change the enemy healthbar after calling addDamage
        enemyHealthSlider.gameObject.SetActive(true); // Visually activate the healthbar when enemy is damaged

        enemyAnim.SetTrigger("enemyTakeHit"); // Do the take hit animation for the enemy...
        AudioSource.PlayClipAtPoint(hitSound, transform.position);

        if(currentEnemyHealth <= 0)
        {
            enemyAnim.SetTrigger("enemyDead");
            kill();
        }
    }

    void kill()
    {
        
        Instantiate(enemyDeathFX, transform.position, transform.rotation);
        Destroy(gameObject.transform.parent.gameObject);

        enemyController.stopMoving();
        AudioSource.PlayClipAtPoint(deathSound, transform.position);

        if(canDrop)
        {
            Instantiate(healthDrop, transform.position, transform.rotation);
        }
        
        if(canSpecialDrop)
        {
            Instantiate(specialDrop, transform.position, transform.rotation);
        }
    }
}
